package edu.it.step.util;

public class PrintManager {
    private PrintManager() {
    }

    public static void println(String text, PrintType printType) {
        switch (printType) {
            case ERROR -> System.err.println(text);
            case COMMON -> println(text);
            default -> throw new IllegalArgumentException("Unknown enum type");
        }
    }

    public static void println(String text) {
        print(text + '\n');
    }

    public static void print(String text) {
        System.out.print(text);
    }

}
