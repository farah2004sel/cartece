package com.mc.icmc.service;


import com.mc.icmc.domain.Roles;
import com.mc.icmc.dto.request.RolesRequest;
import com.mc.icmc.dto.response.LoginResponse;
import com.mc.icmc.dto.response.RolesResponse;

import java.util.List;
import java.util.UUID;

public interface IRoleService {
    RolesResponse createRole(RolesRequest request);
    LoginResponse updateRoleById(Long id, Long user_id, RolesRequest request);
    void deleteRole(Long id);
    List<RolesResponse> getAllRolesResponse();
    Roles getRoleByName(String name);
    RolesResponse getRoleResponseById(Long id);
    Roles getRoleById(Long id);
}
