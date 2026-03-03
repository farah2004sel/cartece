package com.example.cartecom.dto.response;

import java.io.Serializable;

public record LoginResponse(
        String token,
        UserResponse user,
        String email,
        String role,
        boolean verified,
        boolean userEnable
) implements Serializable {

}