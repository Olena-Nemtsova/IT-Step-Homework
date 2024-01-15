package edu.it.step;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChatServer {
    private static final int PORT = 5555;
    private static final int HISTORY_SIZE = 10;
    private static final String HISTORY_FILE = "10-lesson/src/main/resources/chat.txt";
    private static final List<String> chatHistory = Collections.synchronizedList(new ArrayList<>());
    private static final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started...");

            loadChatHistory();

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(ChatServer::clearHistoryFile, 1, 1, TimeUnit.HOURS);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                executorService.submit(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadChatHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatHistory.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void appendToChatHistory(String message) {
        chatHistory.add(message);
        saveChatHistory();
    }

    private static synchronized void saveChatHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE))) {
            int startIndex = Math.max(0, chatHistory.size() - HISTORY_SIZE);
            for (int i = startIndex; i < chatHistory.size(); i++) {
                writer.write(chatHistory.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void clearHistoryFile() {
        chatHistory.clear();
        saveChatHistory();
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                writer.println("Enter your name:");
                String clientName = reader.readLine();

                for (String message : chatHistory) {
                    writer.println(message);
                }

                String enterMessage = clientName + " entered the chat";
                appendToChatHistory(enterMessage);
                broadcastMessage(enterMessage);

                String clientMessage;
                while ((clientMessage = reader.readLine()) != null) {
                    if (clientMessage.equals("###exit###")) {
                        break;
                    }

                    String fullMessage = "[" + getCurrentTime() + "] " + clientName + ": " + clientMessage;
                    appendToChatHistory(fullMessage);
                    broadcastMessage(fullMessage);
                }

                String exitMessage = clientName + " left the chat";
                appendToChatHistory(exitMessage);
                broadcastMessage(exitMessage);

                clients.remove(this);
                System.out.println("Client " + clientName + " came out.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void broadcastMessage(String message) {
            for (ClientHandler client : clients) {
                if (!client.equals(this)) {
                    client.sendMessage(message);
                }
            }
        }

        private void sendMessage(String message) {
            try (PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
                writer.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
