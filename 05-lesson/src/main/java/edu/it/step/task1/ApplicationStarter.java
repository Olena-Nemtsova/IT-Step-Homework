package edu.it.step.task1;

import edu.it.step.task1.model.Scientist;
import edu.it.step.task1.model.Speciality;
import java.util.*;

public class ApplicationStarter {
    public static void main(String[] args) {
        List<Scientist> scientists = ScientistRepository.getScientists();
        /*
        Map<Speciality, List<Scientist>> map = scientists
                .stream()
                .collect(Collectors.groupingBy(Scientist::getSpeciality));
        map.forEach(((speciality, scientistsList) -> {
            System.out.println(speciality);
            scientistsList.forEach(System.out::println);
        }));
        */

        Map<Speciality, List<Scientist>> map = new EnumMap<>(Speciality.class);

        for (Scientist scientist : scientists) {
            if (!map.containsKey(scientist.getSpeciality())) {
                map.put(scientist.getSpeciality(), new ArrayList<>(List.of(scientist)));
            } else {
                map.get(scientist.getSpeciality()).add(scientist);
            }
        }

        for (Map.Entry<Speciality, List<Scientist>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            for (Scientist scientist : entry.getValue()) {
                System.out.println(scientist);
            }
        }
    }
}
