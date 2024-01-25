package edu.it.step;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private final Address address;
    private String firstName;
    private String lastName;
    private int age;

}
