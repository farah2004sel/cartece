package com.mc.icmc.dto.response;

import java.io.Serializable;
import java.util.UUID;

public record ActionsResponse(
        Long actionId,
        String actionTitle
) implements Serializable { }
