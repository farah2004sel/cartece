package com.mc.icmc.service;

import com.mc.icmc.dto.request.LoginRequest;
import com.mc.icmc.dto.request.RegisterEmployeRequest;
import com.mc.icmc.dto.request.RegisterRequest;
import com.mc.icmc.dto.response.LoginResponse;
import com.mc.icmc.dto.response.RegisterResponse;
import com.mc.icmc.dto.response.UserResponse;
import com.mc.icmc.domain.Users;

import java.util.List;

public interface IAuthService {

    RegisterResponse register(RegisterRequest request);

    //RegisterResponse registerEmploye(RegisterEmployeRequest request);


    LoginResponse login(LoginRequest request);

    //  Nouvelle méthode
    List<UserResponse> getAllUsersOrderBySortOrderDesc();

    //  Nouvelle méthode pour archive utilisateur
    UserResponse archiveUserById(Long id);

    void forgotPassword(String email);

    boolean verifyResetKey(String email, String key);

    boolean verifyConfirmeCompte(String email, String code);


    void resetPassword(String email, String key, String newPassword);
    void resendVerificationCode(String email);
    Users getUserById(Long userId);
}
