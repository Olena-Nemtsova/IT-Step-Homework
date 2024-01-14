package edu.it.step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5555;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println(reader.readLine());
            String clientName = scanner.nextLine();
            writer.println(clientName);

            Thread messageReader = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            messageReader.start();
            Thread messageSender = new Thread(() -> {
                try {
                    String clientMessage;
                    while (true) {
                        clientMessage = scanner.nextLine();
                        writer.println(clientMessage);

                        if (clientMessage.equals("###exit###")) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            messageSender.start();

            messageReader.join();
            messageSender.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
