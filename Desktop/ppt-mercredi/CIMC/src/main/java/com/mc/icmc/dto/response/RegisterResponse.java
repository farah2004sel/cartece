package com.mc.icmc.dto.response;

import java.io.Serializable;

public record RegisterResponse(
        String message,
        UserResponse user
) implements Serializable { }
