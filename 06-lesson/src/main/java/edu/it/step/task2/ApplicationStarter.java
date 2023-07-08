package edu.it.step.task2;

public class ApplicationStarter {
    public static void main(String[] args) {
        CopyManager copyManager = new CopyManager();

        copyManager.copyAll(
                "06-lesson/src/main/resources/image",
                "06-lesson/src/main/resources/copy"
        );
    }
}
