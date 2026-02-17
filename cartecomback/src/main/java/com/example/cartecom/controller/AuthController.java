package com.example.cartecom.controller;

import com.example.cartecom.entity.Commercant;
import com.example.cartecom.repository.CommercantRepository;
import com.example.cartecom.service.CommercantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final CommercantService service;
    private final CommercantRepository commercantRepository;

     public AuthController(CommercantService service, CommercantRepository commercantRepository) {
        this.service = service;
        this.commercantRepository = commercantRepository;
    }

     @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Commercant commercant) {
        try {
            Commercant saved = service.registerCommercant(commercant);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Commercant enregistré et code envoyé",
                    "email", saved.getEmail()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }

     @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestBody VerificationRequest request) {
        try {
            boolean ok = service.verifyEmail(request.getEmail(), request.getCode());
            if (ok) {
                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "Email vérifié"
                ));
            } else {
                return ResponseEntity.ok(Map.of(
                        "success", false,
                        "message", "Code incorrect"
                ));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }

    // 🔹 Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        var userOpt = commercantRepository.findByEmail(request.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Email non trouvé"
            ));
        }

        Commercant c = userOpt.get();

        if (!c.getPassword().equals(request.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Mot de passe incorrect"
            ));
        }

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login réussi",
                "userId", c.getId(),
                "email", c.getEmail()
        ));
    }


    // Classes internes
    public static class VerificationRequest {
        private String email;
        private String code;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
