package Main.Server;

import Main.Services.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.out;

public class ServerConnectionHandler implements Runnable {
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
            out.println("<SERVER> " + username + " has connected to the server");
            reader();
        } catch (IOException e) {
            System.out.println("<SERVER> " + username + " has closed the connection!");
            messenger("<SERVER> Auf Wiedersehen", true, false);
        }
    }

    // reads new messages from client
    private void reader() {
        while (true) {
            try {
                String message = bufferedReader.readLine();
                if (message != null) {
                    out.println(username + ": " + message);
                } else {
                    System.out.println("<SERVER> " + username + " has closed the connection!");
                    messenger("<SERVER> Auf Wiedersehen", true, false);
                    break;
                }
                if (message.toLowerCase().equals("exit")) {
                    System.out.println("<SERVER> " + username + " has closed the connection!");
                    messenger("<SERVER> Auf Wiedersehen", true, false);
                    break;
                }
                if (message.toLowerCase().split(" ")[0].equals("calc")) {
                    String[] split = message.split(" ");
                    messenger(split[1] + split[2] + split[3] + "=" + Service.calc(Double.valueOf(split[1]), split[2], Double.valueOf(split[3])), false, true);
                }
                if (message.toLowerCase().split(" ")[0].equals("convert")) {
                    String[] split = message.split(" ");
                    messenger(Service.convertNumberSystem(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])), false, true);
                }
                if (message.toLowerCase().equals("help")) {
                    messenger(Service.man, false, true);
                }
                messenger(message, false, false);
            } catch (IOException e) {
                System.out.println("<SERVER> " + username + " has closed the connection!");
                messenger("<SERVER> Auf Wiedersehen", true, false);
                break;
            }
        }
    }

    // send new message from client to all other clients
    private void messenger(String message, Boolean closed, Boolean service) {
        PrintWriter printWriter;
        if (service) {
            try {
                printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                if (!closed) {
                    printWriter.println("<SERVER> " + message);
                } else {
                    printWriter.println("<SERVER> " + username + " has closed the connection!");
                }
            } catch (IOException e) {
                out.println("<SERVER> " + username + " has closed the connection!");
                Server.socketArrayList.remove(clientSocket);
            }
        }
        if (!service) {
            synchronized (Server.socketArrayList) {
                if (!message.toLowerCase().equals("exit") && !message.toLowerCase().split(" ")[0].equals("calc")) {
                    for (Socket socket : Server.socketArrayList) {
                        // don't send same message back to sender
                        if (socket != clientSocket) {
                            try {
                                printWriter = new PrintWriter(socket.getOutputStream(), true);
                                if (!closed) {
                                    printWriter.println(username + ": " + message);
                                } else {
                                    printWriter.println("<SERVER> " + username + " has closed the connection!");
                                }
                            } catch (IOException e) {
                                out.println("<SERVER> Main.Test.Client.Server.Test.Client.Server.Test.Client has closed the connection");
                                Server.socketArrayList.remove(socket);
                            }
                        }
                    }
                }
            }
            if (closed) {
                try {
                    printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                    printWriter.println(message);
                    synchronized (Server.socketArrayList) {
                        Server.socketArrayList.remove(clientSocket);
                    }
                } catch (IOException e) {
                    System.out.println("<SERVER> " + username + " has closed the connection!");
                    synchronized (Server.socketArrayList) {
                        Server.socketArrayList.remove(clientSocket);
                    }
                }
            }
        }
    }
}
