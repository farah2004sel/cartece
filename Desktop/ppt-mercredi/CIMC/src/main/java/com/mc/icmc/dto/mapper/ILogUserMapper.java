package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Log_User;
import com.mc.icmc.dto.request.LogUserRequest;
import com.mc.icmc.dto.response.LogUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ILogUserMapper {

    // Request -> Entity
    @Mapping(target = "logUserId", ignore = true)
    @Mapping(target = "logUserUuid", ignore = true)
    @Mapping(target = "logUserDate", ignore = true) // sera généré automatiquement
    @Mapping(target = "user.id", source = "userId")     // map userId du request vers user
    @Mapping(target = "module.moduleId", source = "moduleId") // map moduleId du request
    @Mapping(target = "action.actionId", source = "actionId") // map actionId du request
    Log_User toEntity(LogUserRequest request);

    // Entity -> Response
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "moduleId", source = "module.moduleId")
    @Mapping(target = "actionId", source = "action.actionId")
    LogUserResponse toResponse(Log_User logUser);
}
