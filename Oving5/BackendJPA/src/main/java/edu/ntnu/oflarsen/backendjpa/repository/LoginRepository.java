package edu.ntnu.oflarsen.backendjpa.repository;

import edu.ntnu.oflarsen.backendjpa.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByUsername(String username);
}
