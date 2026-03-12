package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Roles;
import com.mc.icmc.dto.request.RolesRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T12:33:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class IRolesMapperImpl implements IRolesMapper {

    @Override
    public Roles toEntity(RolesRequest request) {
        if ( request == null ) {
            return null;
        }

        Roles roles = new Roles();

        roles.setRoleName( request.getRoleName() );

        return roles;
    }
}
