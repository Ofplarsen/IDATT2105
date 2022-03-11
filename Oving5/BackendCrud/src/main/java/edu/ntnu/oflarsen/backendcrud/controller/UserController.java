package edu.ntnu.oflarsen.backendcrud.controller;

import edu.ntnu.oflarsen.backendcrud.model.User;
import edu.ntnu.oflarsen.backendcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user){
        try{
            User user1 = userRepository.findByUsername(user.getUsername());
            if(user1 == null || !user1.getPassword().equals(user.getPassword())){
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
