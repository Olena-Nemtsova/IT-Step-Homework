package edu.it.step.task3.util;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class PrintManager {
    private static final PrintStream OUT = new PrintStream(System.out, true, StandardCharsets.UTF_8);

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
        OUT.print(text);
    }

}
