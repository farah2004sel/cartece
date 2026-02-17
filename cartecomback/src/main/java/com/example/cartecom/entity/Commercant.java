package com.example.cartecom.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "commercant")
public class Commercant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     private String nom;
    private String prenom;

     private String societe;

    private String email;
    private String password;
    private String nationalite;

     private String type;

     private boolean emailVerified = false;
    private String verificationCode;

    // Constructor vide requis par JPA
    public Commercant() {}

     public Commercant(String nom, String prenom, String societe, String email, String password, String nationalite, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.societe = societe;
        this.email = email;
        this.password = password;
        this.nationalite = nationalite;
        this.type = type;
        this.emailVerified = false;
    }
}
