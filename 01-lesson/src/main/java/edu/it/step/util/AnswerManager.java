package edu.it.step.util;

import java.util.Scanner;

public class AnswerManager {
    private static final Scanner SCANNER = new Scanner(System.in);

    private AnswerManager() {
    }

    public static int getAnswer(int maxNumber) {
        try {
            int num = Integer.parseInt(SCANNER.nextLine());
            if (num <= 1 || num > maxNumber) {
                throw new NumberFormatException();
            }
            return num;
        } catch (NumberFormatException e) {
            PrintManager.println(
                    "Error! Input must be integer > 1 and <=" + maxNumber + " please, try again",
                    PrintType.ERROR
            );
            return getAnswer(maxNumber);
        }
    }
}
