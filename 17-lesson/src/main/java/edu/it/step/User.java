package edu.it.step;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
}
