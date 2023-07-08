package edu.it.step.task1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Scientist {
    private String firstName;
    private String lastName;
    private Speciality speciality;
}
