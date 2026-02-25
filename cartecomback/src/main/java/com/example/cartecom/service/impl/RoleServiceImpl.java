package com.example.cartecom.service.impl;

import com.example.cartecom.domain.*;
import com.example.cartecom.dto.mapper.IRolesMapper;
import com.example.cartecom.dto.request.ModuleWithActionIdsRequest;
import com.example.cartecom.dto.request.RolesRequest;
import com.example.cartecom.dto.response.RolesResponse;
import com.example.cartecom.repository.*;
import com.example.cartecom.service.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;
    private final IRolesMapper roleMapper;
    private final IModulesRepository modulesRepository;
    private final IActionsRepository actionsRepository;
    private final IRoleModuleActionRepository roleModuleActionRepository;

    @Override
    public RolesResponse createRole(RolesRequest request) {
        Roles role = roleMapper.toEntity(request);
        if (request.getModules() != null) {
            for (ModuleWithActionIdsRequest moduleRequest : request.getModules()) {
                Modules module = modulesRepository.findById(moduleRequest.getModuleId())
                        .orElseThrow(() -> new IllegalArgumentException("Module non trouvé : " + moduleRequest.getModuleId()));

                for (Long actionId : moduleRequest.getActionIds()) {
                    Actions action = actionsRepository.findById(actionId)
                            .orElseThrow(() -> new IllegalArgumentException("Action non trouvée : " + actionId));

                    RoleModuleAction rma = new RoleModuleAction();
                    rma.setRole(role);
                    rma.setModule(module);
                    rma.setAction(action);

                    role.getRoleModuleActions().add(rma);
                }
            }
        }
        int maxSortOrder = roleRepository.findMaxRoleSortOrder();
        role.setRoleSortOrder(maxSortOrder + 1);
        role.setRoleCancel(0);
        role.setRolePublished(1);
        role.setRoleDateAdded(LocalDate.now());

        Roles savedRole = roleRepository.save(role);
        return roleMapper.toResponse(savedRole);
    }

    @Override
    public RolesResponse updateRoleById(Long roleId, Long moduleId, RolesRequest request) {
        Roles role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role non trouvé"));

        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());

        if (moduleId != null && request.getModules() != null) {
            Modules module = modulesRepository.findById(moduleId)
                    .orElseThrow(() -> new RuntimeException("Module non trouvé"));

            role.getRoleModuleActions().removeIf(rma -> rma.getModule().getModuleId().equals(moduleId));

            request.getModules().stream()
                    .filter(m -> m.getModuleId().equals(moduleId))
                    .flatMap(m -> m.getActionIds().stream())
                    .forEach(actionId -> {
                        Actions action = actionsRepository.findById(actionId)
                                .orElseThrow(() -> new RuntimeException("Action non trouvée : " + actionId));

                        RoleModuleAction rma = new RoleModuleAction();
                        rma.setRole(role);
                        rma.setModule(module);
                        rma.setAction(action);
                        role.getRoleModuleActions().add(rma);
                    });
        }

        Roles updatedRole = roleRepository.save(role);
        return roleMapper.toResponse(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {
        Roles role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role non trouvé"));
        roleRepository.delete(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolesResponse> getAllRolesResponse() {
        return roleRepository.findAllPublishedAndActiveOrdered().stream()
                .map(roleMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<Roles> getRoleByName(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public RolesResponse getRoleResponseById(Long id) {
        Roles role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role non trouvé"));
        return roleMapper.toResponse(role);
    }

    @Override
    public Roles getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role non trouvé"));
    }
}