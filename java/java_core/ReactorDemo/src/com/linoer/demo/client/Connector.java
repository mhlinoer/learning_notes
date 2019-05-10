package com.linoer.demo.client;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Connector implements Runnable {

    private final Selector selector;
    private final SocketChannel socketChannel;

    Connector(SocketChannel socketChannel, Selector selector){
        this.selector = selector;
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            // 连接完成，三次握手完成
            if(socketChannel.finishConnect()){
                System.out.println(String.format(
                        "connect to %s success", socketChannel.getRemoteAddress()
                ));
                new Handler(socketChannel, selector);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
