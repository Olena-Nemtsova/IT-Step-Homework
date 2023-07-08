package edu.it.step.task3.util;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AnswerManager {
    private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

    private AnswerManager() {
    }

    public static int getIntAnswer(int maxNumber) {
        try {
            int num = Integer.parseInt(SCANNER.nextLine());
            if (num < 1 || num > maxNumber) {
                throw new NumberFormatException();
            }
            return num;
        } catch (NumberFormatException e) {
            PrintManager.println(
                    "Error! Input must be integer >= 1 and <=" + maxNumber + " please, try again",
                    PrintType.ERROR
            );
            return getIntAnswer(maxNumber);
        }
    }
}
