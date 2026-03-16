package com.mc.icmc.service.impl;

import com.mc.icmc.domain.Nationalite;
import com.mc.icmc.repository.INationaliteRepository;
import com.mc.icmc.service.INationaliteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationaliteServiceImpl implements INationaliteService {

    private final INationaliteRepository nationaliteRepository;

    public NationaliteServiceImpl(INationaliteRepository nationaliteRepository) {
        this.nationaliteRepository = nationaliteRepository;
    }

    @Override
    public List<Nationalite> getAll() {
        return nationaliteRepository.findAll();
    }

    @Override
    public Nationalite create(Nationalite nationalite) {
        return nationaliteRepository.save(nationalite);
    }
}