package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client {
    private final int port = 21569;
    private final String serverIP = "127.0.0.1";
    private Socket clientSocket;
    private PrintWriter printWriter;
    private Scanner input;
    private BufferedReader bufferedReader;

    Client() {
        try {
            clientSocket = new Socket(serverIP, port);
        } catch (IOException e) {
            System.out.println("<CLIENT> Failed to connect to server");
            exit(1);
        }
        System.out.println("<CLIENT> Connected to server");
        new Thread(this::reader).start();
        try {
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("<CLIENT> Connection to server interrupted");
            exit(1);
        }
        input = new Scanner(System.in);
        System.out.println("Input username: ");
        printWriter.println(input.nextLine());
        writer();
    }

    private void writer() {
        while (true) {
            printWriter.println(input.nextLine());
        }
    }

    private void reader() {
        String message = "";
        while (true) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                message = bufferedReader.readLine();
            } catch (IOException e) {
                System.out.println("<CLIENT> Connection to server interrupted");
                exit(0);
            }
            if (message != null) {
                System.out.println(message);
            } else {
                System.out.println("<CLIENT> Connection to server interrupted");
                exit(1);
            }

            if (message.equals("<SERVER> Auf Wiedersehen")) {
                System.out.println("<CLIENT> Terminating client");
                System.exit(1);
            }
        }
    }
}
