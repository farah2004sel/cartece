package com.mc.icmc.service.impl;

import com.mc.icmc.domain.*;
import com.mc.icmc.dto.mapper.IRolesMapper;
import com.mc.icmc.dto.mapper.IUserMapper;
import com.mc.icmc.dto.request.ModuleWithActionIdsRequest;
import com.mc.icmc.dto.request.RolesRequest;
import com.mc.icmc.dto.response.LoginResponse;
import com.mc.icmc.dto.response.RolesResponse;
import com.mc.icmc.dto.response.UserResponse;
import com.mc.icmc.error.exception.RoleAlreadyExistsException;
import com.mc.icmc.error.exception.RoleNotFoundException;
import com.mc.icmc.repository.*;
import com.mc.icmc.service.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;




    @Override
    public RolesResponse createRole(RolesRequest request) {

        // Mapper le rôle (nom et description)
        Roles role = roleMapper.toEntity(request);

        // Lier les modules et les actions existantes via RoleModuleAction
        if (request.getModules() != null && !request.getModules().isEmpty()) {
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

                    // Ajouter la liaison au rôle
                    role.getRoleModuleActions().add(rma);
                }
            }
        }


        // Récupérer le max actuel du sort order et l'incrémenter
        int maxSortOrder = roleRepository.findMaxRoleSortOrder();
        role.setRoleSortOrder(maxSortOrder + 1);
        role.setRoleCancel(0);
        role.setRolePublished(1);
        role.setRoleDateAdded(LocalDate.now());
        Roles savedRole = roleRepository.save(role);
        return roleMapper.toResponse(savedRole);
    }




    /*****************/
    @Override
    @Transactional
    public LoginResponse updateRoleById(Long id, Long user_id , RolesRequest request) {

        // Récupérer l'utilisateur par username ou email
        Users users = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));


        //  Récupérer le rôle existant
        Roles role = roleRepository.findByRoleId(id)
                .orElseThrow(RoleNotFoundException::new);

//        //historique
//        Roles roleHist = new Roles();
//        BeanUtils.copyProperties(role, roleHist);
//        int maxSortOrder = roleRepository.findMaxRoleSortOrder();
//        roleHist.setRoleSortOrder(maxSortOrder + 1);
//        roleHist.setRoleCancel(0);
//        roleHist.setRoleId(null);
//        roleHist.setRoleUuid(UUID.randomUUID());
//        roleHist.setRolePublished(0);
//        roleHist.setRoleDateAdded(LocalDate.now());
//        roleHist.setParentId(role.getRoleId());
//        Roles savedRoleHist = roleRepository.save(roleHist);
//        //end historique


        // Mettre à jour les champs de base
        role.setRoleName(request.getRoleName());
        // Supprimer les anciennes relations en base
        roleModuleActionRepository.deleteByRole(role);

        //  Très important
        role.getRoleModuleActions().clear();

        //  Recréer les nouvelles relations
        if (request.getModules() != null && !request.getModules().isEmpty()) {
            for (ModuleWithActionIdsRequest moduleRequest : request.getModules()) {
                Modules module = modulesRepository.findById(moduleRequest.getModuleId())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Module non trouvé : " + moduleRequest.getModuleId()
                        ));

                for (Long actionId : moduleRequest.getActionIds()) {
                    Actions action = actionsRepository.findById(actionId)
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "Action non trouvée : " + actionId
                            ));

                    RoleModuleAction rma = new RoleModuleAction();
                    rma.setRole(role);
                    rma.setModule(module);
                    rma.setAction(action);

                    role.getRoleModuleActions().add(rma);
                }
            }
        }


        role.setRolePublished(1);
        role.setRoleCancel(0);
        Roles updatedRole = roleRepository.save(role);

        // Mapper User → UserResponse
        UserResponse userResp = userMapper.toResponse(users);
        String token = null;

        boolean profileCompleted = true;
        return new LoginResponse(token, userResp, profileCompleted);    }
    /***************/


    @Override
    public void deleteRole(Long id) {
        Roles role = roleRepository.findByRoleId(id)
                .orElseThrow(RoleNotFoundException::new);
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
    public Roles getRoleByName(String name) {
        return roleRepository.findByRoleName(name)
                .orElseThrow(RoleNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public RolesResponse getRoleResponseById(Long id) {
        Roles role = roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);
        return roleMapper.toResponse(role);
    }

    @Override
    public Roles getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);
    }
}



