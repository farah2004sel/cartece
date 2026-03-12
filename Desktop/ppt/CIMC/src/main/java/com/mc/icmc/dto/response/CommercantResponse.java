package com.mc.icmc.dto.response;

import java.io.Serializable;

public record CommercantResponse(
        Long id,
        String societe,
        String typePersonne,
        Long userId
) implements Serializable {}