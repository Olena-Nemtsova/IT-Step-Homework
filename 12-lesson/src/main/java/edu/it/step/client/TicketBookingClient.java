package edu.it.step.client;

import edu.it.step.Place;
import lombok.extern.java.Log;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log
public class TicketBookingClient {
    private JFrame frame;
    private JButton[] seatButtons;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private Socket socket;
    private ObjectOutputStream writer;
    private ObjectInputStream objectInputStream;
    private List<Place> seats;
    private static final UUID CLIENT_ID = UUID.randomUUID();

    public TicketBookingClient() {
        initialize();
        connectToServer();
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
        new SwingWorker<Void, List<Place>>() {
            @Override
            protected Void doInBackground() {
                initializeSeatsState();
                return null;
            }

            @Override
            protected void done() {
                addServerResponseListener();
            }
        }.execute();
    }

    private void initialize() {
        frame = new JFrame("Ticket Booking Client");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel seatPanel = new JPanel(new GridLayout(2, 3));
        seatButtons = new JButton[6];
        for (int i = 0; i < 6; i++) {
            seatButtons[i] = new JButton("Seat " + (i + 1));
            int finalI = i;
            seatButtons[i].addActionListener(e -> reserveSeat(finalI));
            seatPanel.add(seatButtons[i]);
        }

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        inputPanel.add(phoneNumberField);

        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.addActionListener(e -> confirmBooking());
        inputPanel.add(confirmButton);

        frame.add(seatPanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 5555);
            writer = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeSeatsState() {
        try {
            List<Place> seatsList = receiveSeatsFromServer();
            SwingUtilities.invokeLater(() -> updateSeatsState(seatsList));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateSeatsState(List<Place> seatsList) {
        for (Place place : seatsList) {
            int seatNumber = place.getNumber() - 1;
            Place.SeatStatus status = place.getStatus();
            updateSeatState(seatNumber, status);
        }
    }

    private void addServerResponseListener() {
        new Thread(() -> {
            try {
                while (true) {
                    List<Place> updatedSeats = receiveSeatsFromServer();
                    SwingUtilities.invokeLater(() -> updateSeatsState(updatedSeats));
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                handleServerDisconnect();
            }
        }).start();
    }

    private List<Place> receiveSeatsFromServer() throws IOException, ClassNotFoundException {
        try {
            seats = (List<Place>) objectInputStream.readObject();
            return seats;
        } catch (SocketException e) {
            handleServerDisconnect();
            throw new IOException("Server disconnected", e);
        }
    }

    private void updateSeatState(int seatNumber, Place.SeatStatus status) {
        if (status != null) {
            Color color = null;
            String text = seatButtons[seatNumber].getText();
            Place seat = seats.stream().filter(place -> place.getNumber() == seatNumber + 1).findFirst().orElse(new Place());

            switch (status) {
                case FREE -> color = Color.GREEN;
                case RESERVED -> color = Color.YELLOW;
                case BOOKED -> {
                    color = Color.RED;
                    text += " of " + seat.getReservedBy();
                }
                default -> log.warning("Unexpected status: %s".formatted(status));
            }
            if (color != null) {
                seatButtons[seatNumber].setBackground(color);
                seatButtons[seatNumber].setEnabled(!status.equals(Place.SeatStatus.BOOKED));
                seatButtons[seatNumber].setText(text);
            }
        }
    }

    private synchronized List<Place> getSeatsSnapshot() {
        return new ArrayList<>(seats);
    }

    private void reserveSeat(int seatNumber) {
        if (seatNumber >= 0 && seatNumber < 6) {
            seats.stream().filter(seat ->
                            CLIENT_ID.equals(seat.getClientId()) && seat.getStatus().equals(Place.SeatStatus.RESERVED))
                    .forEach(seat -> seat.setStatus(Place.SeatStatus.FREE));

            String name = nameField.getText();
            String phoneNumber = phoneNumberField.getText();

            Place place = seats.get(seatNumber);
            place.setStatus(Place.SeatStatus.RESERVED);
            place.setReservedBy(name);
            place.setPhoneNumber(phoneNumber);
            place.setClientId(CLIENT_ID);

            sendToServer();
        }
    }

    private void confirmBooking() {
        int seatNumber = seats.stream().filter(seat ->
                        CLIENT_ID.equals(seat.getClientId()) && seat.getStatus().equals(Place.SeatStatus.RESERVED))
                .mapToInt(Place::getNumber)
                .findFirst().orElse(-1);

        String name = nameField.getText();
        String phoneNumber = phoneNumberField.getText();

        Place place = seats.get(seatNumber - 1);
        place.setStatus(Place.SeatStatus.BOOKED);
        place.setReservedBy(name);
        place.setPhoneNumber(phoneNumber);

        sendToServer();
    }

    private void sendToServer() {
        try {
            writer.writeObject(getSeatsSnapshot());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleServerDisconnect() {
        JOptionPane.showMessageDialog(frame, "Server disconnected. Exiting application.", "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicketBookingClient::new);
    }
}