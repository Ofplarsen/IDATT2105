package edu.ntnu.oflarsen.backendjpa.repository;

import edu.ntnu.oflarsen.backendjpa.model.Equation;
import edu.ntnu.oflarsen.backendjpa.model.Login;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface EquationRepository extends JpaRepository<Equation, Long> {
    @Query(
            value = "SELECT * " +
                    "FROM equations where equations.user_id = ?1 " +
                    "OFFSET (?2 * 10) ROWS " +
                    "FETCH NEXT 10 ROWS ONLY;",
            nativeQuery = true
    )
    List<Equation> findById(int userId, long page);
}
