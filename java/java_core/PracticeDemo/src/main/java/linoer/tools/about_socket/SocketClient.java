package linoer.tools.about_socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 55533;
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        String message = "hello abcdefghij";
        byte[] sendBytes = message.getBytes(StandardCharsets.UTF_8);
        // 先将消息的长度发送出去
        outputStream.write(sendBytes.length >> 8);
        outputStream.write(sendBytes.length);

        // 再发送具体的内容
        outputStream.write(sendBytes);
        outputStream.flush();

        // 第二条消息
        message = "second hello";
        sendBytes = message.getBytes(StandardCharsets.UTF_8);
        // 先将消息的长度发送出去
        outputStream.write(sendBytes.length >> 8);
        outputStream.write(sendBytes.length);

        // 再发送具体的内容
        outputStream.write(sendBytes);
        outputStream.flush();

        message = "third hello";
        sendBytes = message.getBytes(StandardCharsets.UTF_8);
        // 先将消息的长度发送出去
        outputStream.write(sendBytes.length >> 8);
        outputStream.write(sendBytes.length);

        // 再发送具体的内容
        outputStream.write(sendBytes);

        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1){
            sb.append(new String(bytes,0, len, StandardCharsets.UTF_8));
        }

        System.out.println("client get response:" + sb);

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
