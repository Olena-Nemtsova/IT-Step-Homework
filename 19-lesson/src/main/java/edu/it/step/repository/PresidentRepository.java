package edu.it.step.repository;

import edu.it.step.entity.President;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentRepository extends JpaRepository<President, Long> {
}
