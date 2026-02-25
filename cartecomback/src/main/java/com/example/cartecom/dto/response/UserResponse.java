package com.example.cartecom.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        Long id,
        String userName,
        String userEmail,
        String FirstName,
        String LastName,

        String Phone,
        String Key,
        String Code,
        LocalDateTime ConfirmedAt,
        LocalDateTime DateConnect,
        RolesResponse role
) implements Serializable { }
