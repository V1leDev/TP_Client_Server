package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private int port = 21569;
    public static ArrayList<Socket> socketArrayList = new ArrayList<>();
    private ServerSocket serverSocket;

    Server() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("<SERVER> Socket hat been created!");
        } catch (IOException e) {
            System.out.println("<SERVER> Socket could not be created!");
            System.exit(1);
        }
        while(true){
            try {
                Socket clientSocket = serverSocket.accept();
                synchronized (socketArrayList){
                    socketArrayList.add(clientSocket);
                }
                executorService.submit(new ServerConnectionHandler(clientSocket));
            } catch (IOException e) {
                System.out.println("<SERVER> Failed connection to client!");
            }
        }
    }
}
