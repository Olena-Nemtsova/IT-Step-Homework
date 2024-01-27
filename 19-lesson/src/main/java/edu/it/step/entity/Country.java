package edu.it.step.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> officialLanguages;
    private int population;
    @OneToOne(mappedBy = "country")
    private President president;

    @Override
    public String toString() {
        return "Country{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", officialLanguages=" + officialLanguages +
               ", population=" + population +
               ", president={" + "id=" + president.getId() +
               ", firstName='" + president.getFirstName() + '\'' +
               ", lastName='" + president.getLastName() + '\'' +
               ", birthYear=" + president.getBirthYear() +
               '}' +
               '}';
    }
}
