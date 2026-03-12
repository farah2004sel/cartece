package com.mc.icmc.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record LogUserResponse(
        Long logUserId,
        UUID logUserUuid,
        Long userId,
        Long moduleId,
        Long actionId,
        String logUserTitle,
        LocalDateTime logUserDate,
        String logUserIp,
        String macVendeur,
        String logUserAgent,
        String logUserAgentString
) implements Serializable { }
