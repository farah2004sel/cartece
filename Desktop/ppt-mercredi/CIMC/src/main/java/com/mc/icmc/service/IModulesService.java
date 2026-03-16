package com.mc.icmc.service;

import com.mc.icmc.dto.request.ModulesRequest;
import com.mc.icmc.dto.response.ModulesResponse;

import java.util.List;
import java.util.UUID;

public interface IModulesService {

    // Créer un nouveau module
    ModulesResponse createModule(ModulesRequest request);

    // Mettre à jour un module existant
    ModulesResponse updateModule(Long modulId, ModulesRequest request);

    // Supprimer un module par Id
    void deleteModule(Long moduleId);


    // Récupérer la liste de tous les modules
    List<ModulesResponse> getAllModules();
}