package com.example.cartecom.service.impl;

import com.example.cartecom.domain.Users;
import com.example.cartecom.repository.IUserRepository;
import com.example.cartecom.service.IJwtService;
import com.example.cartecom.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IJwtService jwtService;

    @Override
    public Users register(Users user) {

        if (userRepository.existsByUserEmail(user.getUserEmail())) {
            throw new IllegalStateException("Email already exists");
        }

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setUserVerificationCode(UUID.randomUUID().toString());
        user.setUserEnabled(false);

        return userRepository.save(user);
    }

    @Override
    public boolean verifyEmail(String email, String verificationCode) {
        Optional<Users> optUser = userRepository.findByUserEmail(email);
        if (optUser.isEmpty()) return false;

        Users user = optUser.get();
        if (verificationCode.equals(user.getUserVerificationCode())) {
            user.setUserEnabled(true);
            user.setUserVerificationCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String login(String email, String rawPassword) {

        Users user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isUserEnabled()) {
            throw new RuntimeException("Account not verified");
        }

        if (!passwordEncoder.matches(rawPassword, user.getUserPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user);
    }
}