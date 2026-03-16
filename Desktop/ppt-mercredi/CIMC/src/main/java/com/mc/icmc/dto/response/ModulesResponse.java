package com.mc.icmc.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record ModulesResponse(
        Long moduleId,
        String moduleName,
        List<ActionsResponse> actions
) implements Serializable { }
