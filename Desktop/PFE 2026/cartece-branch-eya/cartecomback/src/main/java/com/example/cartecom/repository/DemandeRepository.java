package com.example.cartecom.repository;

import com.example.cartecom.domain.Demande;
import com.example.cartecom.domain.StatusDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    long countByCommercantId(Long commercantId);

    long countByCommercantIdAndStatus(Long commercantId, StatusDemande status);

    Optional<Demande> findTopByCommercantIdOrderByDateDemandeDesc(Long commercantId);
}
