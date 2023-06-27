package edu.it.step.task3;

import edu.it.step.util.AnswerManager;
import edu.it.step.util.PrintManager;
import edu.it.step.util.RandomNumberGenerator;

import java.util.Arrays;

public class PlaceController {
    private int[][] places;

    public void createAndBuy() {
        create();
        buy();
    }

    private void create() {
        PrintManager.println("Input number of rows:");
        int n = AnswerManager.getAnswer(10);
        PrintManager.println("Input number of seats in row:");
        int m = AnswerManager.getAnswer(50);

        PrintManager.println("Places created:");
        places = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                places[i][j] = RandomNumberGenerator.generate(0, 1);
                PrintManager.print(places[i][j] + " ");
            }
            PrintManager.println("");
        }
    }

    private void buy() {
        PrintManager.println("Input number of seats:");
        int numPlacesToBuy = AnswerManager.getAnswer(places[0].length);

        int[] free;
        for (int i = 0; i < places.length; i++) {
            PrintManager.print(i + 1 + " row - ");

            for (int j = 0; j < places[i].length; j++) {
                free = new int[0];

                if (places[i][j] == 0) {
                    while (j < places[i].length && places[i][j] == 0) {
                        free = Arrays.copyOf(free, free.length + 1);
                        free[free.length - 1] = j + 1;
                        j++;
                    }
                    if (free.length >= numPlacesToBuy) {
                        PrintManager.print(Arrays.toString(free) + " ");
                    }
                }
            }
            PrintManager.println("");
        }
    }
}
