package com.mc.icmc.dto.request;

import lombok.Data;

@Data
public class DemandeRequest {

    private String typeDemande;

    private String activiteSollicitee;

    private String adresseEtablissementPrincipal;

    private String adresseSuccursales;

    private Long commercantId;

}