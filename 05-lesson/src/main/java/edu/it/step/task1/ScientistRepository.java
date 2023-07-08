package edu.it.step.task1;

import edu.it.step.task1.model.Scientist;
import edu.it.step.task1.model.Speciality;
import java.util.ArrayList;
import java.util.List;

public class ScientistRepository {
    private static final List<Scientist> scientists = new ArrayList<>();

    private ScientistRepository() {
    }

    static {
        pullScientists();
    }

    private static void pullScientists() {
        scientists.add(new Scientist("Sam", "Smith", Speciality.CHEMISTRY));
        scientists.add(new Scientist("Bob", "Brown", Speciality.CHEMISTRY));
        scientists.add(new Scientist("Kate", "Pearce", Speciality.BIOLOGY));
        scientists.add(new Scientist("John", "Williams", Speciality.PHYSICS));
        scientists.add(new Scientist("Bob", "Jones", Speciality.BIOLOGY));
        scientists.add(new Scientist("Alice", "Miller", Speciality.PHYSICS));
    }

    public static List<Scientist> getScientists(){
        return scientists;
    }
}
