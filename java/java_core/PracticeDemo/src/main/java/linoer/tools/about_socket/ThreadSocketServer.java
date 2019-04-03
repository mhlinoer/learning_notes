package linoer.tools.about_socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSocketServer {
    public static void main(String[] args) throws IOException {
        int port = 55533;
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("waiting ......");

        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        while (true){

            Socket socket = serverSocket.accept();
            Runnable runnable = ()->{
                try {

                }catch (Exception e){
                    e.printStackTrace();
                }
            };

        }


    }
}
