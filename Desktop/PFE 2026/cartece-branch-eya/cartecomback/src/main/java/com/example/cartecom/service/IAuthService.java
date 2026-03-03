package com.example.cartecom.service;

import com.example.cartecom.dto.request.LoginRequest;
import com.example.cartecom.dto.request.RegisterRequest;
import com.example.cartecom.dto.response.LoginResponse;
import com.example.cartecom.dto.response.RegisterResponse;
import com.example.cartecom.dto.response.UserResponse;

import java.util.List;

public interface IAuthService {

    /**
     * Enregistrer un nouvel utilisateur avec rôle COMMERCANT et mot de passe encodé.
     * @param request les informations d'inscription
     * @return réponse avec message et email utilisateur
     */
    RegisterResponse register(RegisterRequest request);

    /**
     * Authentifier un utilisateur par email ou username et générer un JWT.
     * @param request informations de login
     * @return JWT et informations utilisateur
     */
    LoginResponse login(LoginRequest request);

    /**
     * Récupérer tous les utilisateurs actifs, triés par sortOrder descendant.
     * @return liste des utilisateurs
     */
    List<UserResponse> getAllUsersOrderBySortOrderDesc();

    /**
     * Archiver un utilisateur (marquer comme annulé) par son ID.
     * @param id ID de l'utilisateur
     * @return DTO utilisateur après mise à jour
     */
    UserResponse archiveUserById(Long id);

    /**
     * Générer et envoyer une clé de réinitialisation de mot de passe par email.
     * @param email email de l'utilisateur
     */
    void forgotPassword(String email);

    /**
     * Vérifier la validité de la clé de réinitialisation envoyée par email.
     * @param email email de l'utilisateur
     * @param code clé de réinitialisation reçue
     * @return true si la clé est valide
     */
    boolean verifyResetKey(String email, String code);

    /**
     * Réinitialiser le mot de passe après vérification de la clé.
     * @param email email de l'utilisateur
     * @param code clé de réinitialisation reçue
     * @param newPassword nouveau mot de passe
     */
    void resetPassword(String email, String code, String newPassword);
 }