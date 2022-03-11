package edu.ntnu.oflarsen.backendcrud.repository;

import edu.ntnu.oflarsen.backendcrud.model.User;

import java.util.List;

public interface UserRepository {
    int save(User user);
    //int update(User user);
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    int deleteAll();
}
