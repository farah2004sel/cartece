package com.mc.icmc.service.impl;

import com.mc.icmc.domain.Commercant;
import com.mc.icmc.dto.response.CommercantResponse;
import com.mc.icmc.repository.ICommercantRepository;
import com.mc.icmc.service.ICommercantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommercantServiceImpl implements ICommercantService {

    private final ICommercantRepository commercantRepository;

    @Override
    public Commercant save(Commercant commercant) {
        return commercantRepository.save(commercant);
    }

    @Override
    public List<Commercant> findAll() {
        return commercantRepository.findAll();
    }
    @Override
    public List<CommercantResponse> findAllResponse() {
        return commercantRepository.findAll().stream()
                .map(c -> new CommercantResponse(
                        c.getId(),
                        c.getSociete(),
                        c.getTypePersonne(),
                        c.getUser() != null ? c.getUser().getId() : null,
                        c.getNationaliteId(),
                        c.getBirthDate(),
                        c.getBirthPlace(),
                        c.getResidenceDate(),
                        c.getAddress(),
                        c.getLegalForm(),
                        c.getRepName(),
                        c.getRepNationality()
                ))
                .collect(Collectors.toList());
    }
    @Override
    public Commercant findById(Long id) {
        return commercantRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        commercantRepository.deleteById(id);
    }



    @Override
    public Commercant findByUserId(Long userId) {
        return commercantRepository.findByUser_Id(userId).orElse(null);
    }

    @Override
    public Commercant updateProfile(Long userId, Commercant updatedProfile) {

        Commercant existing = commercantRepository.findByUser_Id(userId).orElse(null);

        if (existing == null) {
            existing = new Commercant();
            existing.setUser(updatedProfile.getUser());
        }

        existing.setSociete(updatedProfile.getSociete());
        existing.setTypePersonne(updatedProfile.getTypePersonne());
        existing.setNationaliteId(updatedProfile.getNationaliteId());

        existing.setBirthDate(updatedProfile.getBirthDate());
        existing.setBirthPlace(updatedProfile.getBirthPlace());
        existing.setResidenceDate(updatedProfile.getResidenceDate());
        existing.setAddress(updatedProfile.getAddress());

        existing.setLegalForm(updatedProfile.getLegalForm());
        existing.setRepName(updatedProfile.getRepName());
        existing.setRepNationality(updatedProfile.getRepNationality());

        return commercantRepository.save(existing);
    }
}