package com.example.cartecom.service;

import com.example.cartecom.domain.Roles;
import com.example.cartecom.dto.request.RolesRequest;
import com.example.cartecom.dto.response.RolesResponse;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    RolesResponse createRole(RolesRequest request);

    RolesResponse updateRoleById(Long roleId, Long moduleId, RolesRequest request);

    void deleteRole(Long id);

    List<RolesResponse> getAllRolesResponse();

    Optional<Roles> getRoleByName(String name);

    RolesResponse getRoleResponseById(Long id);

    Roles getRoleById(Long id);
}