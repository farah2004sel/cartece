package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Actions;
import com.mc.icmc.domain.Log_User;
import com.mc.icmc.domain.Modules;
import com.mc.icmc.domain.Users;
import com.mc.icmc.dto.request.LogUserRequest;
import com.mc.icmc.dto.response.LogUserResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T12:33:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class ILogUserMapperImpl implements ILogUserMapper {

    @Override
    public Log_User toEntity(LogUserRequest request) {
        if ( request == null ) {
            return null;
        }

        Log_User log_User = new Log_User();

        log_User.setUser( logUserRequestToUsers( request ) );
        log_User.setModule( logUserRequestToModules( request ) );
        log_User.setAction( logUserRequestToActions( request ) );
        log_User.setLogUserTitle( request.getLogUserTitle() );
        log_User.setLogUserAgent( request.getLogUserAgent() );
        log_User.setLogUserAgentString( request.getLogUserAgentString() );

        return log_User;
    }

    @Override
    public LogUserResponse toResponse(Log_User logUser) {
        if ( logUser == null ) {
            return null;
        }

        Long userId = null;
        Long moduleId = null;
        Long actionId = null;
        Long logUserId = null;
        UUID logUserUuid = null;
        String logUserTitle = null;
        LocalDateTime logUserDate = null;
        String logUserIp = null;
        String macVendeur = null;
        String logUserAgent = null;
        String logUserAgentString = null;

        userId = logUserUserId( logUser );
        moduleId = logUserModuleModuleId( logUser );
        actionId = logUserActionActionId( logUser );
        logUserId = logUser.getLogUserId();
        logUserUuid = logUser.getLogUserUuid();
        logUserTitle = logUser.getLogUserTitle();
        logUserDate = logUser.getLogUserDate();
        logUserIp = logUser.getLogUserIp();
        macVendeur = logUser.getMacVendeur();
        logUserAgent = logUser.getLogUserAgent();
        logUserAgentString = logUser.getLogUserAgentString();

        LogUserResponse logUserResponse = new LogUserResponse( logUserId, logUserUuid, userId, moduleId, actionId, logUserTitle, logUserDate, logUserIp, macVendeur, logUserAgent, logUserAgentString );

        return logUserResponse;
    }

    protected Users logUserRequestToUsers(LogUserRequest logUserRequest) {
        if ( logUserRequest == null ) {
            return null;
        }

        Users users = new Users();

        users.setId( logUserRequest.getUserId() );

        return users;
    }

    protected Modules logUserRequestToModules(LogUserRequest logUserRequest) {
        if ( logUserRequest == null ) {
            return null;
        }

        Modules modules = new Modules();

        modules.setModuleId( logUserRequest.getModuleId() );

        return modules;
    }

    protected Actions logUserRequestToActions(LogUserRequest logUserRequest) {
        if ( logUserRequest == null ) {
            return null;
        }

        Actions actions = new Actions();

        actions.setActionId( logUserRequest.getActionId() );

        return actions;
    }

    private Long logUserUserId(Log_User log_User) {
        if ( log_User == null ) {
            return null;
        }
        Users user = log_User.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long logUserModuleModuleId(Log_User log_User) {
        if ( log_User == null ) {
            return null;
        }
        Modules module = log_User.getModule();
        if ( module == null ) {
            return null;
        }
        Long moduleId = module.getModuleId();
        if ( moduleId == null ) {
            return null;
        }
        return moduleId;
    }

    private Long logUserActionActionId(Log_User log_User) {
        if ( log_User == null ) {
            return null;
        }
        Actions action = log_User.getAction();
        if ( action == null ) {
            return null;
        }
        Long actionId = action.getActionId();
        if ( actionId == null ) {
            return null;
        }
        return actionId;
    }
}
