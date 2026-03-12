package com.mc.icmc.service;

import com.mc.icmc.dto.request.ActionsRequest;
import com.mc.icmc.dto.request.LogUserRequest;
import com.mc.icmc.dto.response.ActionsResponse;

import java.util.List;
import java.util.UUID;

public interface IActionsService {

    // Créer une nouvelle action
    ActionsResponse createAction(ActionsRequest request);

    // Mettre à jour une action existante par ID
    ActionsResponse updateAction(Long actionId, ActionsRequest request);

    // Récupérer une action par ID
    ActionsResponse getActionById(Long actionId);


    // Récupérer toutes les actions
    List<ActionsResponse> getAllActions();

    // Supprimer une action par ID
    void deleteAction(Long actionId);
}
