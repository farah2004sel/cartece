package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Actions;
import com.mc.icmc.dto.request.ActionsRequest;
import com.mc.icmc.dto.response.ActionsResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T12:33:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class IActionsMapperImpl implements IActionsMapper {

    @Override
    public Actions toEntity(ActionsRequest request) {
        if ( request == null ) {
            return null;
        }

        Actions actions = new Actions();

        actions.setActionTitle( request.getActionTitle() );

        return actions;
    }

    @Override
    public ActionsResponse toResponse(Actions action) {
        if ( action == null ) {
            return null;
        }

        Long actionId = null;
        String actionTitle = null;

        actionId = action.getActionId();
        actionTitle = action.getActionTitle();

        ActionsResponse actionsResponse = new ActionsResponse( actionId, actionTitle );

        return actionsResponse;
    }
}
