package edu.it.step.service;

import edu.it.step.entity.Country;
import edu.it.step.entity.President;
import edu.it.step.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class PresidentConsoleService {
    private final PresidentService presidentService;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose the action:\n"
                               + "1. Create president\n"
                               + "2. Create country\n"
                               + "3. Change country president\n\n"
                               + "0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPresident();
                    break;
                case 2:
                    createCountry();
                    break;
                case 3:
                    replacePresident();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error. Try again..");
            }
        }
    }

    private void createPresident() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter president name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter birth year:");
        int birthYear = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter country name:");
        String countryName = scanner.nextLine();

        try {
            President president = presidentService.createPresident(firstName, lastName, birthYear, countryName);
            System.out.println("President created:\n %s".formatted(president));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createCountry() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter country name:");
        String name = scanner.nextLine();
        System.out.println("Enter languages (comma separated):");
        String languagesInput = scanner.nextLine();
        List<String> officialLanguages = new ArrayList<>(Arrays.asList(languagesInput.split(",")));
        System.out.println("Enter population:");
        int population = scanner.nextInt();
        scanner.nextLine();

        Country country = presidentService.createCountry(name, officialLanguages, population);
        System.out.println("Country created:\n %s".formatted(country));
    }

    private void replacePresident() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter country id:");
        Long countryId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter new president name:");
        String newFirstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String newLastName = scanner.nextLine();
        System.out.println("Enter birth year:");
        int newBirthYear = scanner.nextInt();
        scanner.nextLine();

        try {
            Country country = presidentService.replacePresident(countryId, newFirstName, newLastName, newBirthYear);
            System.out.println("President replaced:\n %s".formatted(country));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
