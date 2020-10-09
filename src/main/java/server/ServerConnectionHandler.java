package server;


import services.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerConnectionHandler implements Runnable {
    static final String SERVER = "<SERVER> ";
    static final String BYE = "Auf Wiedersehen";
    static final String CONN_CLOSED = " has closed the connection!";
    static final String CONN_NEW = " has connected to the server";
    static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Socket clientSocket;
    private String username = "Unregistered";
    private BufferedReader bufferedReader;

    ServerConnectionHandler(Socket cs) {
        clientSocket = cs;
    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            username = bufferedReader.readLine();
            String m = SERVER + username + CONN_NEW;
            logger.info(m);
            reader();
        } catch (IOException e) {
            String m = SERVER + username + CONN_CLOSED;
            logger.info(m);
            messenger(SERVER + BYE, true, false);
        }
    }

    // reads new messages from client
    private void reader() {
        while (true) {
            try {
                String message = bufferedReader.readLine();
                if (message == null || message.equalsIgnoreCase("exit")) {
                    String m = SERVER + username + CONN_CLOSED;
                    logger.info(m);
                    messenger(SERVER + BYE, true, false);
                    break;
                }
                String m = username + ": " + message;
                logger.info(m);
                if (message.toLowerCase().split(" ")[0].equals("calc") && checkFormatCalc(message.toLowerCase().split(" "))) {
                    String[] split = message.split(" ");
                    messenger(split[1] + split[2] + split[3] + "=" + Services.calc(Double.valueOf(split[1]), split[2], Double.valueOf(split[3])), false, true);
                }
                if (message.toLowerCase().split(" ")[0].equals("convert") && checkFormatConvert(message.toLowerCase().split(" "))) {
                    String[] split = message.split(" ");
                    messenger(Services.convertNumberSystem(split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3])), false, true);
                }
                if (message.equalsIgnoreCase("help")) {
                    messenger(Services.MAN, false, true);
                }
                messenger(message, false, false);
            } catch (IOException e) {
                logger.info(SERVER + username + CONN_CLOSED);
                messenger(SERVER + BYE, true, false);
                return;
            }
        }
    }


    private boolean checkFormatCalc(String[] split) {
        try {
            Double.valueOf(split[1]);
            Double.valueOf(split[3]);
            return true;
        } catch (NumberFormatException exception) {
            messenger("Input has wrong format!", false, true);
            return false;
        }
    }

    private boolean checkFormatConvert(String[] split){
        try {
            Integer.parseInt(split[1], Integer.parseInt(split[2]));
            return true;
        }catch (NumberFormatException exception){
            messenger("Input has wrong format!", false, true);
            return false;
        }
    }

    // send new message from client to all other clients
    private void messenger(String message, boolean closed, boolean service) {
        if (service) {
            isService(message, closed);
        }
        if (!service) {
            notService(message, closed);
        }
    }

    private void isService(String message, boolean closed) {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            if (!closed) {
                printWriter.println(SERVER + message);
            } else {
                printWriter.println(SERVER + username + CONN_CLOSED);
            }
        } catch (IOException e) {
            logger.info(SERVER + username + CONN_CLOSED);
            Server.socketArrayList.remove(clientSocket);
        }
    }

    private void notService(String message, boolean closed) {
        synchronized (Server.socketArrayList) {
            if (!message.equalsIgnoreCase("exit") && !message.toLowerCase().split(" ")[0].equals("calc")) {
                for (Socket socket : Server.socketArrayList) {
                    // don't send same message back to sender
                    if (socket != clientSocket) {
                        notClientSocket(socket, message, closed);
                    }
                }
            }
        }
        if (closed) {
            isClosed(message);
        }
    }

    private void notClientSocket(Socket socket, String message, boolean closed) {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            if (!closed) {
                printWriter.println(username + ": " + message);
            } else {
                printWriter.println(SERVER + username + CONN_CLOSED);
            }
        } catch (IOException e) {
            logger.info(SERVER + "Client has closed the connection");
            Server.socketArrayList.remove(socket);
        }
    }

    private void isClosed(String message) {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            printWriter.println(message);
            synchronized (Server.socketArrayList) {
                Server.socketArrayList.remove(clientSocket);
            }
        } catch (IOException e) {
            logger.info(SERVER + username + CONN_CLOSED);
            synchronized (Server.socketArrayList) {
                Server.socketArrayList.remove(clientSocket);
            }
        }
    }
}

