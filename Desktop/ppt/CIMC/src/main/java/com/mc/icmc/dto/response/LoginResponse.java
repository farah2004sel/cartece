package com.mc.icmc.dto.response;

import java.io.Serializable;

public record LoginResponse(
        String token,
        UserResponse user
) implements Serializable { }
