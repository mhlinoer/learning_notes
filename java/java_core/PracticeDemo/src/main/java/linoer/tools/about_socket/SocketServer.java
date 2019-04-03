package linoer.tools.about_socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        int port = 55533;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server waiting ~~~~~");
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len;
//        StringBuilder sb = new StringBuilder();
//        while ((len = inputStream.read(bytes)) != -1){
//            sb.append(new String(bytes, 0, len, "UTF-8"));
//        }

        byte[] bytes;
        StringBuilder sb = new StringBuilder();
        while (true){
            int first = inputStream.read();
            if(first == -1){
                break;
            }
            int second = inputStream.read();
            int length = (first << 8) + second;
            bytes = new byte[length];
            int re_len = inputStream.read(bytes);
            sb.append(new String(bytes, 0, re_len, StandardCharsets.UTF_8));
            System.out.println("server get sub message:" + new String(bytes, StandardCharsets.UTF_8));
        }
        System.out.println("server get message from client:" + sb);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hey man, i got it".getBytes(StandardCharsets.UTF_8));

        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
    }
}
