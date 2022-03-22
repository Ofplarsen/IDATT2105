package edu.ntnu.oflarsen.backendjpa.service;

import edu.ntnu.oflarsen.backendjpa.model.Login;
import edu.ntnu.oflarsen.backendjpa.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    private Login loggedIn;

    public Login okLogin(String username){
        return loginRepository.findByUsername(username);
    }

    public boolean login(Login login){
        Login _login = loginRepository.findByUsername(login.getUsername());
        if(_login == null || !login.getPassword().equals(_login.getPassword())) {
            return false;
        }
        loggedIn = _login;
        return true;
    }

    public boolean checkLogin(Login login){
        Login _login = loginRepository.findByUsername(login.getUsername());
        return _login != null && login.getPassword().equals(_login.getPassword());
    }

    public Login getLoggedIn() {
        return loggedIn;
    }
}
