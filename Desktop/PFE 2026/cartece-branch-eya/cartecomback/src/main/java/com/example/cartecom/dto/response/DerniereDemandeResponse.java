package com.example.cartecom.dto.response;

import java.time.LocalDate;

public record DerniereDemandeResponse(
        String reference,
        String type,
        LocalDate date,
        String status
) {}
