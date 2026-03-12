package com.mc.icmc.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record RolesResponse(
        Long roleId,
        String roleName,
        List<ModulesResponse> modules
) implements Serializable { }
