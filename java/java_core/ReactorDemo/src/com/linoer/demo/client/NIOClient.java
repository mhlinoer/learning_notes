package com.linoer.demo.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient implements Runnable {

    private Selector selector;
    private SocketChannel socketChannel;

    NIOClient(String ip, int port){
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(ip, port));
            // 入口,注册一个连接事件
            SelectionKey sk = socketChannel.register(selector, SelectionKey.OP_CONNECT);
            // 附加处理类，第一次初始化放的是连接就绪处理类
            sk.attach(new Connector(socketChannel, selector));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey k){
        Runnable runnable = (Runnable) k.attachment();
        if(runnable != null){
            runnable.run();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                // 就绪事件到达之前阻塞
                selector.select();
                // 拿到本地select获取的就绪事件
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()){
                    dispatch((SelectionKey) it.next());
                }
                selected.clear();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
