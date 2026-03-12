package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Actions;
import com.mc.icmc.dto.request.ActionsRequest;
import com.mc.icmc.dto.response.ActionsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IActionsMapper {

    //  Request -> Entity
    @Mapping(target = "actionId", ignore = true)
    Actions toEntity(ActionsRequest request);

    //  Entity -> Response
    ActionsResponse toResponse(Actions action);
}
