package com.mc.icmc.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        Long id,
        String userName,
        String userEmail,
        String userFirstName,
        String userLastName,

        String userPhone,
        String userKey,
        String userCode,
        LocalDateTime userConfirmedAt,
        LocalDateTime userDateConnect,
        RolesResponse role
) implements Serializable { }
