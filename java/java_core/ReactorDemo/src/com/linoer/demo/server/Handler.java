package com.linoer.demo.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 具体逻辑，读写操作等
 * @author Administrator
 *
 */
public class Handler implements Runnable{

    private final SelectionKey selectionKey;
    private final SocketChannel socketChannel;

    private ByteBuffer reaBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(2048);

    private final static int READ = 0;

    private final static int SEND = 1;

    private int status = READ;

    Handler(SocketChannel socketChannel,Selector selector) throws IOException {
        // 拿到客户端连接
        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);
        // 将该客户端连接注册到selector中，得到一个key
        selectionKey = socketChannel.register(selector, 0);
        // 添加一个handler对象，run方法用以处理业务
        selectionKey.attach(this);
        // 标记读事件为感兴趣事件
        selectionKey.interestOps(SelectionKey.OP_READ);
        // 唤起select阻塞
        selector.wakeup();
    }

    private void read() throws IOException {
        if(selectionKey.isValid()) {
            reaBuffer.clear();
            // read方法结束，以为本次读就绪变为读完毕，标记着一次就绪事件的结束
            int count = socketChannel.read(reaBuffer);
            if(count > 0) {
                System.out.println("accept a message from :" +
                        socketChannel.getRemoteAddress() + " with message:" +
                        new String(reaBuffer.array()));
                status = SEND;
            }
            // 注册写方法
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }else {
            // 此时表示客户端已经断开连接了
            // 将对应的selectKey从selector中删除
            // 否则下次还会select到，因为断开连接意味着读就绪不会变成读完毕，也不cancel，下次select会不停收到该事件
            selectionKey.cancel();
            socketChannel.close();
            System.out.println("when reading , connection lost");
        }
    }

    private void send() throws IOException {
        if (selectionKey.isValid()) {
            sendBuffer.clear();
            sendBuffer.put(
                    String.format("recieve a message: %s from: %s",
                            new String(reaBuffer.array()),
                            socketChannel.getRemoteAddress()).getBytes()
            );
            sendBuffer.flip();
            // 本次事件写就绪变为写完毕
            int count = socketChannel.write(sendBuffer);
            if(count < 0){
                selectionKey.cancel();
                socketChannel.close();
                System.out.println("when sending , connection lost");
//                return;
            }
            // 再次切换到读
            status = READ;
            selectionKey.interestOps(SelectionKey.OP_READ);
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            switch (status) {
                case READ:
                    read();
                    break;
                case SEND:
                    send();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
