package edu.it.step.task1;

import edu.it.step.util.AnswerManager;
import edu.it.step.util.PrintManager;
import edu.it.step.util.RandomNumberGenerator;

public class GuessNumberGame {
    private static final String GREETINGS = """
            ** Guess The Number Game **
            I guessed a number from 1 to 100, you have 10 attempts to guess it.
            During the game I'll give you hints :)
            """;
    private static final String WON = """
            You won !
            """;
    private static final String LOSE = """
            You lose ! Do you want to try again ?
            1 - Yes
            2 - No
            """;

    public void start() {
        int attempts = 10;
        int numberToGuess = RandomNumberGenerator.generate(1, 100);
        PrintManager.println(GREETINGS);

        while (attempts > 0) {
            PrintManager.println(attempts + " tries left");

            PrintManager.println("Input number:");
            int userAttempt = AnswerManager.getAnswer(100);
            if (userAttempt == numberToGuess) {
                PrintManager.println(WON);
                return;
            }
            String result = userAttempt > numberToGuess ? "My number less" : "My number more";
            PrintManager.println(result);

            attempts--;
        }

        PrintManager.println(LOSE);
        if (AnswerManager.getAnswer(100) == 1) {
            start();
        }
    }
}
