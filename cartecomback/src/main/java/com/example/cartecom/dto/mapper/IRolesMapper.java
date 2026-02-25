package com.example.cartecom.dto.mapper;

import com.example.cartecom.domain.Roles;
import com.example.cartecom.dto.request.RolesRequest;
import com.example.cartecom.dto.response.ModulesResponse;
import com.example.cartecom.dto.response.RolesResponse;
import com.example.cartecom.dto.response.ActionsResponse;
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
                .collect(Collectors.groupingBy(rma -> rma.getModule()))
                .entrySet().stream()
                .map(entry -> {
                    var module = entry.getKey();
                    List<ActionsResponse> varActions;
                    try {
                        varActions = entry.getValue().stream()
                                .map(rma -> rma.getAction())
                                .distinct()
                                .map(action -> new ActionsResponse(
                                        action.getActionId(),
                                        action.getActionTitle(),
                                        null // description peut rester null si non défini
                                ))
                                .collect(Collectors.toList());
                    } catch (Exception e) {
                        varActions = List.of();
                    }

                    return new ModulesResponse(
                            module.getModuleId(),
                            module.getModuleName(),
                            varActions
                    );
                })
                .collect(Collectors.toList());

        return new RolesResponse(
                role.getRoleId(),
                role.getRoleName(),
                modules
        );
    }
}