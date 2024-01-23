package edu.it.step.sever;

import edu.it.step.Place;
import lombok.extern.java.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Log
public class TicketBookingServer {
    private static final int PORT = 5555;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private List<Place> seats;

    public TicketBookingServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            executorService = Executors.newCachedThreadPool();
            seats = new CopyOnWriteArrayList<>();
            for (int i = 0; i < 6; i++) {
                seats.add(new Place(i + 1, Place.SeatStatus.FREE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        log.info("Server started on port " + PORT);
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TicketBookingServer server = new TicketBookingServer();
        server.start();
    }

    private class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        private synchronized List<Place> getSeatsSnapshot() {
            return new ArrayList<>(seats);
        }

        @Override
        public void run() {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                objectOutputStream.writeObject(getSeatsSnapshot());

                while (true) {
                    seats = (List<Place>) objectInputStream.readObject();
                    objectOutputStream.writeObject(getSeatsSnapshot());
                    log.info(seats.stream().map(Place::toString).collect(Collectors.joining("\n")));
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}