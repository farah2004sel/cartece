package com.example.cartecom.service;

import com.example.cartecom.domain.StatusDemande;
import com.example.cartecom.dto.response.DashboardResponse;
import com.example.cartecom.dto.response.DerniereDemandeResponse;
import com.example.cartecom.repository.DemandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DemandeRepository demandeRepository;

    public DashboardResponse getDashboard(Long commercantId) {

        long total = demandeRepository.countByCommercantId(commercantId);
        long enAttente = demandeRepository
                .countByCommercantIdAndStatus(commercantId, StatusDemande.EN_ATTENTE);

        long validee = demandeRepository
                .countByCommercantIdAndStatus(commercantId, StatusDemande.VALIDEE);

        var derniere = demandeRepository
                .findTopByCommercantIdOrderByDateDemandeDesc(commercantId)
                .map(d -> new DerniereDemandeResponse(
                        d.getReference(),
                        d.getType(),
                        d.getDateDemande(),
                        d.getStatus().name()
                ))
                .orElse(null);

        return new DashboardResponse(
                total,
                enAttente,
                validee,
                derniere
        );
    }
}
