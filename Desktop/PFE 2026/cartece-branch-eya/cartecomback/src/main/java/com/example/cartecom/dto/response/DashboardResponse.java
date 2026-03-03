package com.example.cartecom.dto.response;

public record DashboardResponse(
        long totalDemandes,
        long demandesEnAttente,
        long demandesValidees,
        DerniereDemandeResponse derniereDemande
) {}
