package edu.ntnu.oflarsen.backendjpa.controller;

import edu.ntnu.oflarsen.backendjpa.model.Login;
import edu.ntnu.oflarsen.backendjpa.service.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin
public class TokenController {
    @Autowired
    private LoginService service;

    public static String keyStr = "testsecrettestsecrettestsecrettestsecrettestsecret";

    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(@RequestParam("username") final String username, @RequestParam("password") final String password) throws Exception {
        if (service.checkLogin(new Login(username, password))) {
            return generateToken(username);
        }
        return "Access denied, wrong credentials....";
    }

    public String generateToken(String userId) throws Exception {
        Key key = Keys.hmacShaKeyFor(keyStr.getBytes("UTF-8"));
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("userId", userId);
        claims.put("authorities", grantedAuthorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000000))
                .signWith(key)
                .compact();
    }

}
