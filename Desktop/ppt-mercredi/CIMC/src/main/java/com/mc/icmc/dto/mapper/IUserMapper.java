package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Users;
import com.mc.icmc.dto.request.RegisterEmployeRequest;
import com.mc.icmc.dto.request.RegisterRequest;
import com.mc.icmc.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {IRolesMapper.class} //  on indique qu'on utilisera le mapper des rôles
)
public interface IUserMapper {

    // Request -> Entity (RegisterRequest vers Users)
    default Users toEntity(RegisterRequest request) {
        if (request == null) return null;

        Users user = new Users();
        user.setUserName(request.getUsername());
        user.setUserEmail(request.getEmail());
        user.setUserPassword(request.getPassword());
        user.setUserFirstName(request.getFirstName());
        user.setUserLastName(request.getLastName());

        user.setUserPhone(request.getPhone());
        user.setUserKey(request.getKey());
        user.setUserCode(request.getCode());
        return user;
    }

    // Request -> Entity (RegisterRequest vers Users)
    default Users RegisterEmployeToEntity(RegisterEmployeRequest request) {
        if (request == null) return null;

        Users user = new Users();
        user.setUserName(request.getUsername());
        user.setUserEmail(request.getEmail());
        user.setUserPassword(request.getPassword());
        user.setUserFirstName(request.getFirstName());
        user.setUserLastName(request.getLastName());

        user.setUserPhone(request.getPhone());
        user.setUserKey(request.getKey());
        user.setUserCode(request.getCode());
        return user;
    }

    //  Entity -> Response (Users vers UserResponse)
    @Mapping(target = "role", source = "role")
    UserResponse toResponse(Users users);
}
