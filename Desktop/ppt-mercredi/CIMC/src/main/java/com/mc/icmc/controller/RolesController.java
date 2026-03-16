package com.mc.icmc.controller;

import com.mc.icmc.dto.request.RolesRequest;
import com.mc.icmc.dto.response.LoginResponse;
import com.mc.icmc.dto.response.RolesResponse;
import com.mc.icmc.service.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Contrôleur REST pour la gestion des rôles.
 * Permet de créer, mettre à jour, récupérer et supprimer des rôles
 * avec liaison aux modules existants via leurs IDs.
 *
 * Les réponses utilisent {@link RolesResponse}.
 * Les requêtes utilisent {@link RolesRequest}.
 *
 * @author Ghazi Ben Yahya
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/roles")
@Slf4j
public class RolesController {

    private final IRoleService roleService;

    /**
     * Retrieves all roles.
     * Returns a list of all roles mapped to {@link RolesResponse} objects.
     *
     * @return a {@link ResponseEntity} containing a list of {@link RolesResponse} objects
     */
    @GetMapping("/")
    public ResponseEntity<List<RolesResponse>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRolesResponse());
    }



    /**
     * Creates a new role.
     *
     * @param rolesRequest the role data
     * @return a {@link ResponseEntity} containing the created {@link RolesResponse}
     */
    @PostMapping("/")
    public ResponseEntity<RolesResponse> createRole(
            @Valid @RequestBody RolesRequest rolesRequest) {
        RolesResponse response = roleService.createRole(rolesRequest);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/role/{id}/user/{user_id}")
    public ResponseEntity<LoginResponse> updateRoleById(
            @PathVariable Long id,
            @PathVariable Long user_id,
            @Valid @RequestBody RolesRequest rolesRequest) {
        LoginResponse response = roleService.updateRoleById(id, user_id, rolesRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a role by its UUID.
     *
     * @param id the UUID of the role to delete
     * @return a {@link ResponseEntity} with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesResponse> getRoleById(@PathVariable Long id) {
        RolesResponse response = roleService.getRoleResponseById(id);
        return ResponseEntity.ok(response);
    }

}
