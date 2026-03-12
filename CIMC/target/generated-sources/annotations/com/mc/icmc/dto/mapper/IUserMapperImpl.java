package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Users;
import com.mc.icmc.dto.response.RolesResponse;
import com.mc.icmc.dto.response.UserResponse;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T12:33:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Autowired
    private IRolesMapper iRolesMapper;

    @Override
    public UserResponse toResponse(Users users) {
        if ( users == null ) {
            return null;
        }

        RolesResponse role = null;
        Long id = null;
        String userName = null;
        String userEmail = null;
        String userFirstName = null;
        String userLastName = null;
        String userPhone = null;
        String userKey = null;
        String userCode = null;
        LocalDateTime userConfirmedAt = null;
        LocalDateTime userDateConnect = null;

        role = iRolesMapper.toResponse( users.getRole() );
        id = users.getId();
        userName = users.getUserName();
        userEmail = users.getUserEmail();
        userFirstName = users.getUserFirstName();
        userLastName = users.getUserLastName();
        userPhone = users.getUserPhone();
        userKey = users.getUserKey();
        userCode = users.getUserCode();
        userConfirmedAt = users.getUserConfirmedAt();
        userDateConnect = users.getUserDateConnect();

        UserResponse userResponse = new UserResponse( id, userName, userEmail, userFirstName, userLastName, userPhone, userKey, userCode, userConfirmedAt, userDateConnect, role );

        return userResponse;
    }
}
