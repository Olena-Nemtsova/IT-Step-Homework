package edu.it.step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class User {
    private Address address;
    private String firstName;
    private String lastName;

}
