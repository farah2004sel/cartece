package com.mc.icmc.service.impl;

import com.mc.icmc.domain.Actions;
import com.mc.icmc.domain.Modules;
import com.mc.icmc.dto.mapper.IModulesMapper;
import com.mc.icmc.dto.request.ModulesRequest;
import com.mc.icmc.dto.response.ModulesResponse;
import com.mc.icmc.error.exception.ModuleAlreadyExistsException;
import com.mc.icmc.error.exception.ModuleNotFoundException;
import com.mc.icmc.repository.IActionsRepository;
import com.mc.icmc.repository.IModulesRepository;
import com.mc.icmc.service.IModulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ModulesServiceImpl implements IModulesService {

    private final IModulesRepository modulesRepository;
    private final IModulesMapper modulesMapper;
    private final IActionsRepository actionsRepository;


    @Override
    public ModulesResponse createModule(ModulesRequest request) {

        // Mapper les champs de base (nom, description)
        Modules module = modulesMapper.toEntity(request);

        // Récupérer le max actuel du sort order et l'incrémenter
        int maxSortOrder = modulesRepository.findMaxModuleSortOrder();
        module.setModuleSortOrder(maxSortOrder + 1);

        Modules saved = modulesRepository.save(module);
        return modulesMapper.toResponse(saved);
    }


    @Override
    public ModulesResponse updateModule(Long moduleId, ModulesRequest request) {
        // Récupérer le module existant
        Modules existing = modulesRepository.findById(moduleId)
                .orElseThrow(ModuleNotFoundException::new);

        // Mettre à jour les champs de base
        existing.setModuleName(request.getModuleName());


        Modules updated = modulesRepository.save(existing);

        return modulesMapper.toResponse(updated);
    }

    @Override
    public void deleteModule(Long moduleId) {
        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(ModuleNotFoundException::new);
        modulesRepository.delete(module);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ModulesResponse> getAllModules() {
        return modulesRepository.findAllByOrderByModuleSortOrderDesc()
                .stream()
                .map(modulesMapper::toResponse)
                .toList();
    }
}
