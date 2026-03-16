package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Modules;
import com.mc.icmc.domain.RoleModuleAction;
import com.mc.icmc.dto.request.ModulesRequest;
import com.mc.icmc.dto.response.ActionsResponse;
import com.mc.icmc.dto.response.ModulesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IModulesMapper {

    // Request -> Entity (création de module)
    @Mapping(target = "moduleId", ignore = true)
    Modules toEntity(ModulesRequest request);

    // Entity -> Response avec actions
    default ModulesResponse toResponse(Modules module) {
        List<ActionsResponse> actions = module.getRoleModuleActions().stream()
                .map(RoleModuleAction::getAction)
                .distinct() // Évite les doublons si le module est lié à plusieurs rôles
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
    }
}
