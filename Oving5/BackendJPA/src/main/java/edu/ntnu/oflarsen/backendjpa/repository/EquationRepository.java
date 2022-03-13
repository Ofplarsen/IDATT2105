package edu.ntnu.oflarsen.backendjpa.repository;

import edu.ntnu.oflarsen.backendjpa.model.Equation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquationRepository extends JpaRepository<Equation, Long> {

}
