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


    @Column(name = "nationalite_id")
    private Long nationaliteId;

    // Relation One-to-One avec Users
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)

    private Users user;

}
