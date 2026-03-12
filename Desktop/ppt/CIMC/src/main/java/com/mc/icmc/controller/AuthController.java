package com.mc.icmc.controller;

import com.mc.icmc.dto.request.ActionsRequest;
import com.mc.icmc.dto.request.LoginRequest;
import com.mc.icmc.dto.request.RegisterEmployeRequest;
import com.mc.icmc.dto.request.RegisterRequest;
import com.mc.icmc.dto.response.ActionsResponse;
import com.mc.icmc.dto.response.LoginResponse;
import com.mc.icmc.dto.response.RegisterResponse;
import com.mc.icmc.dto.response.UserResponse;
import com.mc.icmc.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mc.icmc.dto.request.VerifyEmailRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final IAuthService authService;

    /**
     * Endpoint pour l'enregistrement d'un nouvel utilisateur.
     *
     * @param registerRequest les informations de l'utilisateur à enregistrer
     * @return RegisterResponse avec les informations de l'utilisateur créé
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = authService.register(registerRequest);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/verify-account")
    public ResponseEntity<?> verifyAccount(@RequestBody VerifyEmailRequest request) {

        boolean isVerified = authService.verifyConfirmeCompte(request.getEmail(), request.getCode());

        if(isVerified){
            return ResponseEntity.ok().body(
                    Map.of("success", true, "message", "Compte confirmé avec succès")
            );
        }else{
            return ResponseEntity.badRequest().body(
                    Map.of("success", false, "message", "Code invalide ou expiré")
            );
        }
    }
    @PostMapping("/resend-code")
    public ResponseEntity<?> resendCode(@RequestBody Map<String,String> request) {

        String email = request.get("email");

        authService.resendVerificationCode(email);

        return ResponseEntity.ok(
                Map.of("success", true, "message", "Un nouveau code a été envoyé")
        );
    }

    /**
     * Endpoint pour la connexion d'un utilisateur.
     *
     * @param loginRequest les informations de connexion (username/email + mot de passe)
     * @return LoginResponse avec le token JWT et les informations utilisateur
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    // récupérer tous les users triés
    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return authService.getAllUsersOrderBySortOrderDesc();
    }


    /**
     * Archie user existante.
     *
     * @param id       l'identifiant d User
     * @return user mise à jour
     */
    @PutMapping("/archive/{id}")
    public ResponseEntity<UserResponse> archiveUserById(
            @PathVariable Long id) {
        UserResponse response = authService.archiveUserById(id);
        return ResponseEntity.ok(response);
    }

    //les service de mot de passe oublie
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {

        authService.forgotPassword(email);

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Code envoyé à votre email"
                )
        );
    }

    @PostMapping("/verify-key")
    public ResponseEntity<?> verifyKey(@RequestParam String email, @RequestParam String key) {
        boolean isValid = authService.verifyResetKey(email, key);
        return ResponseEntity.ok(isValid ? "Clé valide." : "Clé invalide.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestParam String email,
            @RequestParam String key,
            @RequestParam String newPassword
    ) {

        authService.resetPassword(email, key, newPassword);

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Mot de passe réinitialisé avec succès"
                )
        );
    }

}
