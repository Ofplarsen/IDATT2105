package edu.ntnu.oflarsen.backendjpa.controller;

import edu.ntnu.oflarsen.backendjpa.model.Login;
import edu.ntnu.oflarsen.backendjpa.model.Tutorial;
import edu.ntnu.oflarsen.backendjpa.repository.LoginRepository;
import edu.ntnu.oflarsen.backendjpa.service.CalculatorService;
import edu.ntnu.oflarsen.backendjpa.service.LoginService;
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

    private static Login loggedIn;
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService service;

    @GetMapping("/login/{username}")
    public ResponseEntity<Login> getLoginById(@PathVariable("username") String username) {
        Login data = service.okLogin(username);
        logger.info("Tried to retrieve data from user: " + username);
        if(data == null)
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody Login login) {
        try {
            if(!service.login(login)) {
                logger.info("Login failed: " + login.getUsername());
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }
            logger.info("Login Successful: " + login.getUsername());
            loggedIn = service.getLoggedIn();
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Login getLoggedIn(){
        return loggedIn;
    }

}
