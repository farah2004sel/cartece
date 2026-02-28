package com.example.cartecom.controller;

import com.example.cartecom.dto.request.RegisterRequest;
import com.example.cartecom.dto.request.LoginRequest;
import com.example.cartecom.dto.request.VerifyEmailRequest;
import com.example.cartecom.dto.response.RegisterResponse;
import com.example.cartecom.dto.response.LoginResponse;
import com.example.cartecom.dto.response.UserResponse;
import com.example.cartecom.domain.Users;
import com.example.cartecom.service.IAuthService;
import com.example.cartecom.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final IAuthService authService;
    private final IUserRepository userRepository;

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(Map.of("success", false, "message", ex.getReason()));
        }
    }

    // ================= VERIFY EMAIL =================
    @PostMapping("/verify-email")
    public ResponseEntity<Map<String, Object>> verifyEmail(@RequestBody VerifyEmailRequest request) {
        try {
            String email = request.getEmail().trim();
            String code = request.getCode().trim();

            Optional<Users> optUser = userRepository.findByUserEmail(email);
            if (optUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Utilisateur non trouvé"));
            }

            Users user = optUser.get();
            log.info("DEBUG: user code = {}, code sent = {}", user.getUserVerificationCode(), code);

            if (user.getUserVerificationCode() != null && user.getUserVerificationCode().equals(code)) {
                user.setUserEnabled(true);
                user.setUserVerificationCode(null);
                userRepository.save(user);

                return ResponseEntity.ok(Map.of("success", true, "message", "Email vérifié avec succès"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("success", false, "message", "Code de vérification invalide"));
            }
        } catch (Exception ex) {
            log.error("Erreur verify-email", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Erreur serveur, réessayez plus tard"));
        }
    }

    // ================= USERS =================
    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return authService.getAllUsersOrderBySortOrderDesc();
    }

    // ================= ARCHIVE =================
    @PutMapping("/archive/{id}")
    public ResponseEntity<UserResponse> archiveUserById(@PathVariable Long id) {
        return ResponseEntity.ok(authService.archiveUserById(id));
    }

    // ================= PASSWORD RESET =================
    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, Object>> forgotPassword(@RequestParam String email) {
        try {
            authService.forgotPassword(email);
            return ResponseEntity.ok(Map.of("success", true, "message", "Clé de réinitialisation envoyée à l'email."));
        } catch (Exception ex) {
            log.error("Erreur forgot-password", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Erreur serveur, réessayez plus tard"));
        }
    }

    @PostMapping("/verify-key")
    public ResponseEntity<Map<String, Object>> verifyKey(@RequestParam String email, @RequestParam String key) {
        try {
            boolean isValid = authService.verifyResetKey(email, key);
            return ResponseEntity.ok(Map.of("success", isValid, "message", isValid ? "Clé valide." : "Clé invalide."));
        } catch (Exception ex) {
            log.error("Erreur verify-key", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Erreur serveur, réessayez plus tard"));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestParam String email,
                                                             @RequestParam String key,
                                                             @RequestParam String newPassword) {
        try {
            authService.resetPassword(email, key, newPassword);
            return ResponseEntity.ok(Map.of("success", true, "message", "Mot de passe réinitialisé avec succès."));
        } catch (Exception ex) {
            log.error("Erreur reset-password", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Erreur serveur, réessayez plus tard"));
        }
    }
}