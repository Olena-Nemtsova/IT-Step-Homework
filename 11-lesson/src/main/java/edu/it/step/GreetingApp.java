package edu.it.step;

import javax.swing.*;
import java.awt.*;

public class GreetingApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GreetingApp::createWelcomeWindow);
    }

    private static void createWelcomeWindow() {
        JFrame welcomeFrame = new JFrame("Welcome Window");
        welcomeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        welcomeFrame.setSize(300, 200);
        welcomeFrame.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Enter your name:");
        JTextField nameField = new JTextField(15);
        JButton greetButton = new JButton("Greet");

        greetButton.addActionListener(e -> {
            String userName = nameField.getText();
            showGreetingWindow(userName);
            welcomeFrame.dispose();
        });

        welcomeFrame.add(nameLabel);
        welcomeFrame.add(nameField);
        welcomeFrame.add(greetButton);

        welcomeFrame.setVisible(true);
    }

    private static void showGreetingWindow(String userName) {
        JFrame greetingFrame = new JFrame("Greeting Window");
        greetingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        greetingFrame.setSize(300, 200);
        greetingFrame.setLayout(new FlowLayout());

        JLabel greetingLabel = new JLabel("Hello, " + userName + "!");
        greetingFrame.add(greetingLabel);

        greetingFrame.setVisible(true);
    }
}
