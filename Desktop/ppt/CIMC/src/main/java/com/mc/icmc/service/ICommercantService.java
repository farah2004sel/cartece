package com.mc.icmc.service;

import com.mc.icmc.domain.Commercant;
import java.util.List;
import com.mc.icmc.dto.response.CommercantResponse;
public interface ICommercantService {

    Commercant save(Commercant commercant);

    List<Commercant> findAll();

    Commercant findById(Long id);

    void delete(Long id);
    List<CommercantResponse> findAllResponse();
}