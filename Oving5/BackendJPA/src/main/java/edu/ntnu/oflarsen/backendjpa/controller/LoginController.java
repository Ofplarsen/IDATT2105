package edu.ntnu.oflarsen.backendjpa.controller;

import edu.ntnu.oflarsen.backendjpa.model.Login;
import edu.ntnu.oflarsen.backendjpa.model.Tutorial;
import edu.ntnu.oflarsen.backendjpa.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginRepository loginRepository;

    @GetMapping("/login/{username}")
    public ResponseEntity<Login> getTutorialById(@PathVariable("username") String username) {
        Login loginData = loginRepository.findByUsername(username);
        if (loginData != null) {
            logger.info("Retrieved: " + loginData.getUsername());
            return new ResponseEntity<>(loginData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody Login login) {
        try {
            Login _login = loginRepository.findByUsername(login.getUsername());
            if(_login == null || !login.getPassword().equals(_login.getPassword())) {
                logger.info("Login failed: " + login.getUsername());
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }
            logger.info("Login Successful: " + login.getUsername());
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
