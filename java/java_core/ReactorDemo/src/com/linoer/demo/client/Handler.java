package com.linoer.demo.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class Handler implements Runnable {
    private final SelectionKey selectionKey;
    private final SocketChannel socketChannel;

    private ByteBuffer readBuffer = ByteBuffer.allocate(2048);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    private final static int READ = 0;
    private final static int SEND = 1;

    private int status = SEND;

    private AtomicInteger counter = new AtomicInteger();

    Handler(SocketChannel socketChannel, Selector selector) throws IOException {
        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);
        selectionKey = socketChannel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_WRITE);
        selector.wakeup();
    }

    private void send() throws IOException {
        if(selectionKey.isValid()){
            sendBuffer.clear();
            int count = counter.incrementAndGet();
            if(count <= 10){
                sendBuffer.put(String.format(
                        "this is the %s  second message", count
                ).getBytes());
                sendBuffer.flip();
                socketChannel.write(sendBuffer);
                // 切换到读，接受服务端响应
                status = READ;
                selectionKey.interestOps(SelectionKey.OP_READ);
            }else {
                selectionKey.cancel();
                socketChannel.close();
            }
        }
    }

    private void read() throws IOException {
        if(selectionKey.isValid()){
            readBuffer.clear();
            socketChannel.read(readBuffer);
            status = SEND;
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }
    }

    @Override
    public void run() {
        try {
            switch (status){
                case SEND:
                    send();
                    break;
                case READ:
                    read();
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
            selectionKey.cancel();
            try {
                socketChannel.close();
            }catch (Exception error){
                error.printStackTrace();            }
        }
    }
}
