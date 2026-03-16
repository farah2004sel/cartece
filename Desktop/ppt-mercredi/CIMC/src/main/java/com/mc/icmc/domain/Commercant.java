package com.mc.icmc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commercant")

public class Commercant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Type de personne: 'physique' ou 'morale'
    @Column(name = "type_personne", length = 50)
    private String typePersonne;

    // Nom de la société si personne morale
    @Column(name = "societe", length = 150)
    private String societe;

    // ID de Nationalité (pourrait être une relation @ManyToOne si entité Nationalite existe,
    // mais on utilise Long pour simplifier/correspondre à ce qu'on reçoit du form)
    @Column(name = "nationalite_id")
    private Long nationaliteId;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "residence_date")
    private String residenceDate;

    @Column(name = "address")
    private String address;

    @Column(name = "legal_form")
    private String legalForm;

    @Column(name = "rep_name")
    private String repName;

    @Column(name = "rep_nationality")
    private String repNationality;



    // Relation One-to-One avec Users
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)

    private Users user;

}
