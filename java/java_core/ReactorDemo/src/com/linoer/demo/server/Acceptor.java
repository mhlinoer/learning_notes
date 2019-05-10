package com.linoer.demo.server;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 只负责处理连接就绪事件，通过就绪事件就可以拿到客户端的socketChannel，进而处理后面的读写任务
 * @author Administrator
 *
 */
public class Acceptor implements Runnable{

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    Acceptor(ServerSocketChannel serverSocketChannel,Selector selector) {
        // TODO Auto-generated constructor stub
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        // 客户端的channel
        SocketChannel socketChannel;
        try {
            socketChannel = serverSocketChannel.accept();
            if(null != socketChannel) {
                System.out.println("accept a connection from:" + socketChannel.getRemoteAddress());
                //这里把客户端channel传给handler，handler负责事件处理
                new Handler(socketChannel, selector);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
