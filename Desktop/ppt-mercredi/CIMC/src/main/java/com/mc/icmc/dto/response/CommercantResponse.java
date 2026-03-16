package com.mc.icmc.dto.response;

import java.io.Serializable;

public record CommercantResponse(
        Long id,
        String societe,
        String typePersonne,
        Long userId,
        Long nationaliteId,
        String birthDate,
        String birthPlace,
        String residenceDate,
        String address,
        String legalForm,
        String repName,
        String repNationality
) implements Serializable {}