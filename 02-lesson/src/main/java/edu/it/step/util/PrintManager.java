package edu.it.step.util;

public class PrintManager {
    private PrintManager() {
    }

    public static void println(Object o) {
        println(o.toString());
    }

    public static void println() {
        println("");
    }

    public static void println(String text) {
        print(text + '\n');
    }

    public static void print(String text) {
        System.out.print(text);
    }

}
