package edu.it.step;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ContactConsoleService {
    private final ContactService contactService;

    public void startConsole() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add contact");
            System.out.println("0. Exit");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    contactService.addContact();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    System.out.println(contactService.getAll());
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
