package com.example.cartecom.service;

import com.example.cartecom.entity.User;
import com.example.cartecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User register(User user) {
         user.setVerificationCode(UUID.randomUUID().toString());
        user.setEnabled(false);
         return userRepository.save(user);
    }


    public boolean verifyEmail(String email, String verificationCode) {
        User user = userRepository.findByEmail(email);
        if (user == null) return false;

        if (user.getVerificationCode().equals(verificationCode)) {
            user.setEnabled(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            return true;
        }

        return false;
    }
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.isEnabled() && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}
