package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Modules;
import com.mc.icmc.domain.RoleModuleAction;
import com.mc.icmc.domain.Roles;
import com.mc.icmc.dto.request.RolesRequest;
import com.mc.icmc.dto.response.ActionsResponse;
import com.mc.icmc.dto.response.ModulesResponse;
import com.mc.icmc.dto.response.RolesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IRolesMapper {

    /**
     * Convertit un RolesRequest en Roles entity.
     *
     * Note : la liste des modules est ignorée ici car la liaison
     * avec les modules existants se fait dans le service.
     */
    @Mapping(target = "roleId", ignore = true)
    @Mapping(target = "roleModuleActions", ignore = true)
    Roles toEntity(RolesRequest request);

    /**
     * Convertit une entity Roles en RolesResponse,
     * en incluant les modules et leurs actions.
     */
    default RolesResponse toResponse(Roles role) {
        // Grouper les RoleModuleAction par module
        List<ModulesResponse> modules = role.getRoleModuleActions().stream()
                .collect(Collectors.groupingBy(RoleModuleAction::getModule))
                .entrySet().stream()
                .map(entry -> {
                    Modules module = entry.getKey();
                    List<ActionsResponse> actions = entry.getValue().stream()
                            .map(RoleModuleAction::getAction)
                            .distinct()
                            .map(action -> new ActionsResponse(
                                    action.getActionId(),
                                    action.getActionTitle()
                            ))
                            .toList();

                    return new ModulesResponse(
                            module.getModuleId(),
                            module.getModuleName(),
                            actions
                    );
                })
                .toList();


        return new RolesResponse(
                role.getRoleId(),
                role.getRoleName(),
                modules
        );
    }
}
