package edu.it.step;

import edu.it.step.util.PrintManager;

public class ApplicationStarter {
    public static void main(String[] args) {
        UserArrayList users = new UserArrayList();

        users.add(new User("Mark", "Pearce", 22));
        users.add(new User("Kate", "Pearce", 22));
        users.add(new User("John", "Brown", 22));
        users.forEach(PrintManager::println);
        PrintManager.println();

        users.addToStart(new User("Sam", "Smith", 25));
        users.forEach(PrintManager::println);
        PrintManager.println();

        users.delete(2);
        users.deleteFromStart();

        users.forEach(PrintManager::println);
    }
}
