package com.example.cartecom.dto.response;

import java.io.Serializable;

public record RegisterResponse(
        String message,
        String email,
        boolean verified
) implements Serializable {
}