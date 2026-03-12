package com.mc.icmc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "demande")
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demande_id")
    private Long demandeId;


    @Column(name = "type_demande", length = 50)
    private String typeDemande;

    @Column(name = "activite_sollicitee", columnDefinition = "TEXT")
    private String activiteSollicitee;

    @Column(name = "adresse_etablissement_principal", length = 255)
    private String adresseEtablissementPrincipal;

    @Column(name = "adresse_succursales", columnDefinition = "TEXT")
    private String adresseSuccursales;

    @Column(name = "date_demande")
    private LocalDateTime dateDemande = LocalDateTime.now();

    @Column(name = "status", length = 50)
    private String status = "PENDING";

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commercant_id", nullable = false)
    private Commercant commercant;



    // attributs system
    @Column(name = "demande_date_added")
    private LocalDate demandeDateAdded;

    @Column(name = "demande_date_edit")
    private LocalDate demandeDateEdit;

    @Column(name = "demande_published")
    private int demandePublished;

    @Column(name = "demande_focus")
    private int demandeFocus;

    @Column(name = "demande_cancel")
    private int demandeCancel = 0;

    @Column(name = "demande_sort_order")
    private int demandeSortOrder;
    @Column(name = "demande_parent_id")
    private Long demandeParentId;


}
