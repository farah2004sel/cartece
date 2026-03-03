package com.example.cartecom.service;

import com.example.cartecom.domain.Users;
import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {

    String generateToken(Users users);

    boolean validateToken(String token);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}