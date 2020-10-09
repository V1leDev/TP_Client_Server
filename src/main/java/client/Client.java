package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class Client {
    private boolean notDone = true;
    static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final int PORT = 21569;
    private static final String SERVERIP = "127.0.0.1";
    private Socket clientSocket;
    private static final String CONN_INTERRUPT = "<CLIENT> Failed to connect to server";
    private PrintWriter printWriter;
    private Scanner input;
    private BufferedReader bufferedReader;

    Client() {
        try {
            clientSocket = new Socket(SERVERIP, PORT);
        } catch (IOException e) {
            logger.severe(CONN_INTERRUPT);
            exit(1);
        }
        logger.info("<CLIENT> Connected to server");
        new Thread(this::reader).start();
        try {
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            logger.severe(CONN_INTERRUPT);
            exit(1);
        }
        input = new Scanner(System.in);
        logger.info("Input username: ");
        printWriter.println(input.nextLine());
        writer();
    }

    private void writer() {
        while (notDone) {
            printWriter.println(input.nextLine());
        }
    }

    private void reader() {
        String message = "";
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            logger.severe(CONN_INTERRUPT);
            notDone = false;
            exit(1);
        }
        while (true) {
            try {
                message = bufferedReader.readLine();
            } catch (IOException e) {
                logger.severe(CONN_INTERRUPT);
                notDone = false;
                exit(1);
            }
            if (message != null) {
                logger.info(message);
            } else {
                logger.severe(CONN_INTERRUPT);
                notDone = false;
                exit(1);
            }
            if (message.equals("<SERVER> Auf Wiedersehen")) {
                logger.info("<CLIENT> Terminating client");
                break;
            }
        }
        notDone = false;
        exit(1);
    }
}
