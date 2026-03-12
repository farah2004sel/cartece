package com.mc.icmc.controller;

import com.mc.icmc.dto.request.ActionsRequest;
import com.mc.icmc.dto.response.ActionsResponse;
import com.mc.icmc.service.IActionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des Actions.
 * Chaque Action a un titre unique et peut être liée à un Module.
 *
 * @author Ghazi Ben Yahya
 */
@RestController
@RequestMapping("/api/v1/actions")
@RequiredArgsConstructor
public class ActionsController {

    private final IActionsService actionsService;

    /**
     * Créer une nouvelle action.
     *
     * @param request       les informations de l'action
     * @return l'action créée
     */
    @PostMapping("/")
    public ResponseEntity<ActionsResponse> createAction(
            @RequestBody ActionsRequest request) {
        ActionsResponse response = actionsService.createAction(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Récupérer toutes les actions.
     *
     * @return la liste des actions
     */
    @GetMapping("/")
    public ResponseEntity<List<ActionsResponse>> getAllActions() {
        List<ActionsResponse> responses = actionsService.getAllActions();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer une action par son ID.
     *
     * @param actionId       l'identifiant de l'action
     * @return l'action correspondante
     */
    @GetMapping("/{actionId}")
    public ResponseEntity<ActionsResponse> getActionById(
            @PathVariable Long actionId) {
        ActionsResponse response = actionsService.getActionById(actionId);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour une action existante.
     *
     * @param actionId       l'identifiant de l'action
     * @param request        les nouvelles informations de l'action
     * @return l'action mise à jour
     */
    @PutMapping("/{actionId}")
    public ResponseEntity<ActionsResponse> updateAction(
            @PathVariable Long actionId,
            @RequestBody ActionsRequest request) {
        ActionsResponse response = actionsService.updateAction(actionId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer une action.
     *
     * @param actionId       l'identifiant de l'action à supprimer
     * @return une réponse vide
     */
    @DeleteMapping("/{actionId}")
    public ResponseEntity<Void> deleteAction(
            @PathVariable Long actionId) {
        actionsService.deleteAction(actionId);
        return ResponseEntity.noContent().build();
    }
}
