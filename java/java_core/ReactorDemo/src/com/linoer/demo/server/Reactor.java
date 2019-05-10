package com.linoer.demo.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable{

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    /**
     * reactor初始化
     * @param port
     * @throws IOException
     */
    public Reactor(int port) throws IOException {
        // 打开selector
        selector = Selector.open();
        // 建立一个server通道
        serverSocketChannel = ServerSocketChannel.open();
        // 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        // selector是入口，最初给一个channel注册的事件都是accept
        SelectionKey sKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 绑定
        sKey.attach(new Acceptor(serverSocketChannel, selector));
    }

    private void dispatch(SelectionKey k){
        // 这里很关键，拿到每次selectKey里面附带的处理对象，然后调用其run，
        // 这个对象在具体的Handler里会进行创建，初始化的附带对象为Acceptor（看上面构造器）
        Runnable runnable = (Runnable) k.attachment();
        if(null != runnable) {
            runnable.run();
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while (!Thread.interrupted()) {
                // 就绪事件到达之前，阻塞
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    // 任务分发
                    dispatch((SelectionKey) it.next());
                }
                selected.clear();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
