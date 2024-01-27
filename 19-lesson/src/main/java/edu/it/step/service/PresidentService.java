package edu.it.step.service;

import edu.it.step.entity.Country;
import edu.it.step.entity.President;
import edu.it.step.exception.NotFoundException;
import edu.it.step.repository.CountryRepository;
import edu.it.step.repository.PresidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PresidentService {
    private final PresidentRepository presidentRepository;
    private final CountryRepository countryRepository;

    public President createPresident(String firstName, String lastName, int birthYear, String countryName) {
        President president = new President();
        president.setFirstName(firstName);
        president.setLastName(lastName);
        president.setBirthYear(birthYear);

        Country country = countryRepository.findFirstByName(countryName);
        country = country == null
                ? Country.builder().name(countryName).build()
                : country;

        if (country.getPresident() != null) {
            throw new IllegalArgumentException("President already exists at country: %s".formatted(country));
        }

        president.setCountry(country);

        return presidentRepository.save(president);
    }

    public Country createCountry(String name, List<String> officialLanguages, int population) {
        Country country = Country.builder()
                .name(name)
                .officialLanguages(officialLanguages)
                .population(population)
                .build();

        return countryRepository.save(country);
    }

    public Country replacePresident(Long countryId, String newFirstName, String newLastName, int newBirthYear) {
        Country country = countryRepository.findById(countryId).orElse(null);
        if (country != null) {
            President newPresident = President.builder()
                    .firstName(newFirstName)
                    .lastName(newLastName)
                    .birthYear(newBirthYear)
                    .country(country)
                    .build();

            President oldPresident = country.getPresident();
            if (oldPresident != null) {
                presidentRepository.delete(oldPresident);
            }

            presidentRepository.save(newPresident);
            country.setPresident(newPresident);
            return countryRepository.save(country);
        }
        throw new NotFoundException("Not found country by id: %s".formatted(countryId));
    }
}
