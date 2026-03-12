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
                        c.getUser() != null ? c.getUser().getId() : null
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
}