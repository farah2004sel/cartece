package com.mc.icmc.controller;

import com.mc.icmc.dto.request.ModulesRequest;
import com.mc.icmc.dto.response.ModulesResponse;
import com.mc.icmc.service.IModulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Contrôleur REST pour la gestion des Modules.
 * Chaque module peut contenir plusieurs actions.
 *
 * @author
 * Ghazi Ben Yahya
 */
@RestController
@RequestMapping("/api/v1/modules")
@RequiredArgsConstructor
public class ModulesController {

    private final IModulesService modulesService;

    /**
     * Créer un nouveau module.
     *
     * @param request les informations du module à créer
     * @return le module créé
     */
    @PostMapping("/")
    public ResponseEntity<ModulesResponse> createModule(
            @RequestBody ModulesRequest request) {
        ModulesResponse response = modulesService.createModule(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Récupérer tous les modules.
     *
     * @return la liste de tous les modules
     */
    @GetMapping("/")
    public ResponseEntity<List<ModulesResponse>> getAllModules() {
        List<ModulesResponse> responses = modulesService.getAllModules();
        return ResponseEntity.ok(responses);
    }



    /**
     * Mettre à jour un module existant.
     *
     * @param moduleId l'identifiant unique du module
     * @param request    les nouvelles informations du module
     * @return le module mis à jour
     */
    @PutMapping("/{moduleUuid}")
    public ResponseEntity<ModulesResponse> updateModule(
            @PathVariable Long moduleId,
            @RequestBody ModulesRequest request) {
        ModulesResponse response = modulesService.updateModule(moduleId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer un module.
     *
     * @param moduleId l'identifiant unique du module à supprimer
     * @return une réponse vide
     */
    @DeleteMapping("/{moduleId}")
    public ResponseEntity<Void> deleteModule(
            @PathVariable Long moduleId) {
        modulesService.deleteModule(moduleId);
        return ResponseEntity.noContent().build();
    }
}
