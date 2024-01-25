package edu.it.step;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Address {
    private String street = "default";
    private String houseNumber;
    private String apartmentNumber;
}
