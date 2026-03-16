package com.mc.icmc.controller;

import com.mc.icmc.domain.Commercant;
import com.mc.icmc.dto.response.CommercantResponse;
import com.mc.icmc.service.ICommercantService;
import com.mc.icmc.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mc.icmc.domain.Users;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/commercants")
@RequiredArgsConstructor
public class CommercantController {

    private final ICommercantService commercantService;
    private final IAuthService authService;

     @GetMapping("/profile/{userId}")
    public ResponseEntity<CommercantResponse> getProfile(@PathVariable Long userId) {
        Commercant c = commercantService.findByUserId(userId);
        if (c == null) {
            return ResponseEntity.notFound().build();
        }
        CommercantResponse response = new CommercantResponse(
                c.getId(),
                c.getSociete(),
                c.getTypePersonne(),
                c.getUser() != null ? c.getUser().getId() : null,
                c.getNationaliteId(),
                c.getBirthDate(),
                c.getBirthPlace(),
                c.getResidenceDate(),
                c.getAddress(),
                c.getLegalForm(),
                c.getRepName(),
                c.getRepNationality()
        );
        return ResponseEntity.ok(response);
    }

     @PutMapping("/profile/{userId}")
    public ResponseEntity<CommercantResponse> updateProfile(
            @PathVariable Long userId,
            @RequestBody Commercant commercant) {

        Commercant updated = commercantService.updateProfile(userId, commercant);
        CommercantResponse response = new CommercantResponse(
                updated.getId(),
                updated.getSociete(),
                updated.getTypePersonne(),
                updated.getUser() != null ? updated.getUser().getId() : null,
                updated.getNationaliteId(),
                updated.getBirthDate(),
                updated.getBirthPlace(),
                updated.getResidenceDate(),
                updated.getAddress(),
                updated.getLegalForm(),
                updated.getRepName(),
                updated.getRepNationality()
        );
        return ResponseEntity.ok(response);
    }

     @PostMapping("/create-profile/{userId}")
    public ResponseEntity<CommercantResponse> createProfile(
            @PathVariable Long userId,
            @RequestBody Commercant commercant) {

         Commercant existing = commercantService.findByUserId(userId);
        if (existing != null) {
            return ResponseEntity.badRequest().body(null);
        }

         commercant.setUser(authService.getUserById(userId));

        Commercant saved = commercantService.save(commercant);
        CommercantResponse response = new CommercantResponse(
                saved.getId(),
                saved.getSociete(),
                saved.getTypePersonne(),
                saved.getUser() != null ? saved.getUser().getId() : null,
                saved.getNationaliteId(),
                saved.getBirthDate(),
                saved.getBirthPlace(),
                saved.getResidenceDate(),
                saved.getAddress(),
                saved.getLegalForm(),
                saved.getRepName(),
                saved.getRepNationality()
        );

        return ResponseEntity.ok(response);
    }

     @GetMapping("/")
    public List<CommercantResponse> getAllCommercants() {
        return commercantService.findAllResponse();
    }
}