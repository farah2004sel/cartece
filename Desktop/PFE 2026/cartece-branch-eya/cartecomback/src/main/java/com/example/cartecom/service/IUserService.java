package com.example.cartecom.service;

import com.example.cartecom.domain.Users;

public interface IUserService {


    Users register(Users user);
    boolean verifyEmail(String email, String verificationCode);

    String login(String email, String password);

}