package edu.it.step.repository;

import edu.it.step.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findFirstByName(String name);
}
