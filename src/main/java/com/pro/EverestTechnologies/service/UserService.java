package com.pro.EverestTechnologies.service;

import com.pro.EverestTechnologies.model.Users;
import com.pro.EverestTechnologies.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepo repo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        // Encode the password
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }


    public String verify(Users user) {
        try {
            // Authenticate using the username and raw password (BCrypt will handle matching)
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            // Check if the authentication was successful
            if (authentication.isAuthenticated()) {
                // Generate and return JWT token if authenticated successfully
                return jwtService.generateToken(user.getUsername());
            }
        } catch (Exception e) {
            // Log the error and return "fail" in case of authentication failure
            System.out.println("Authentication failed: " + e.getMessage());
        }
        return "fail";
    }


    }
