package com.example.cartecom.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "demande_carte")
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "type")
    private String type;

    @Column(name = "date_demande")
    private LocalDate dateDemande;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusDemande status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commercant_id")
    private Users commercant;
}
