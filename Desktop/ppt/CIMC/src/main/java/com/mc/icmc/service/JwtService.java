package com.mc.icmc.service;

import com.mc.icmc.domain.Users;

public interface JwtService {
    String generateToken(Users users);
    boolean validateToken(String token);
    String extractUsername(String token);
}

