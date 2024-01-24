package edu.it.step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Address {
    private String street;
    private String houseNumber;
    private String apartmentNumber;
}
