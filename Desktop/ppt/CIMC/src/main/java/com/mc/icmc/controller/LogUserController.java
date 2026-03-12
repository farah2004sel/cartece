package com.mc.icmc.controller;

import com.mc.icmc.dto.request.LogUserRequest;
import com.mc.icmc.dto.response.LogUserResponse;
import com.mc.icmc.service.ILogUserService;
import com.mc.icmc.service.impl.MacAddressUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Contrôleur REST pour la gestion des journaux utilisateurs (Logs).
 * Il permet d’enregistrer, consulter et supprimer les logs relatifs
 * aux actions effectuées par les utilisateurs dans l’application.
 *
 * Chaque log contient notamment :
 * - L’utilisateur concerné
 * - Le module et l’action réalisés
 * - L’adresse IP et la MAC du poste client
 *
 * @author Ghazi
 */
@RestController
@RequestMapping("/api/v1/logs")
@RequiredArgsConstructor
public class LogUserController {

    private final ILogUserService logUserService;

    /**
     * Crée un nouveau log utilisateur.
     *
     * @param request     les informations du log utilisateur
     * @param httpRequest la requête HTTP utilisée pour récupérer l'adresse IP du client
     * @return le log utilisateur créé
     */
    @PostMapping("/")
    public ResponseEntity<LogUserResponse> createLog(@RequestBody LogUserRequest request, HttpServletRequest httpRequest) {
        String clientIp = MacAddressUtil.getClientIp(httpRequest);
        LogUserResponse response = logUserService.createLogUser(request, clientIp);
        return ResponseEntity.ok(response);
    }

    /**
     * Récupère un log utilisateur par son UUID.
     *
     * @param uuid l'identifiant unique du log
     * @return le log correspondant
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<LogUserResponse> getLogByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(logUserService.getLogUserByUuid(uuid));
    }

    /**
     * Récupère la liste de tous les logs utilisateurs enregistrés.
     *
     * @return la liste des logs utilisateurs
     */
    @GetMapping("/")
    public ResponseEntity<List<LogUserResponse>> getAllLogs() {
        return ResponseEntity.ok(logUserService.getAllLogUsers());
    }

    /**
     * Supprime un log utilisateur par son UUID.
     *
     * @param uuid l'identifiant unique du log à supprimer
     * @return une réponse vide
     */
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteLog(@PathVariable UUID uuid) {
        logUserService.deleteLogUser(uuid);
        return ResponseEntity.noContent().build();
    }
}
