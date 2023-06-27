package edu.it.step.task2;

import java.util.Arrays;

public class ApplicationStarter {
    public static void main(String[] args) {
        int[] arr = {6, 4, 4, 4, 5, 5, 1, 2, 5, 3, 6};

        //without streams
        int[] arr2 = new int[0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    while (arr[i + 1] == arr[j]) {
                        i++;
                    }
                    arr2 = Arrays.copyOf(arr2, arr2.length + 1);
                    arr2[arr2.length - 1] = arr[i];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr2));

        //with
        System.out.println(
                Arrays.toString(Arrays.stream(arr)
                        .filter(el -> Arrays.stream(arr).filter(el2 -> el == el2).count() > 1)
                        .distinct()
                        .toArray()
                ));
    }
}
