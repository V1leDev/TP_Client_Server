package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class Server {
    boolean loop = true;
    static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private int port = 21569;
    protected static ArrayList<Socket> socketArrayList = new ArrayList<>();
    private ServerSocket serverSocket;

    Server() {
        try {
            serverSocket = new ServerSocket(port);
            logger.info("<SERVER> Socket hat been created!");
        } catch (IOException e) {
            logger.severe("<SERVER> Socket could not be created!");
            System.exit(1);
        }
        while (loop) {
            try {
                Socket clientSocket = serverSocket.accept();
                synchronized (socketArrayList) {
                    socketArrayList.add(clientSocket);
                }
                executorService.submit(new ServerConnectionHandler(clientSocket));
            } catch (IOException e) {
                logger.severe("<SERVER> Failed connection to client!");
            }
        }
    }
}
