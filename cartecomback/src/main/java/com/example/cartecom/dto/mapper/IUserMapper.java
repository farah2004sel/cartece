package com.example.cartecom.dto.mapper;

import com.example.cartecom.domain.Users;
import com.example.cartecom.dto.request.RegisterRequest;
import com.example.cartecom.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface IUserMapper {

     @Mapping(target = "userName",     source = "nom")
    @Mapping(target = "userEmail",    source = "email")
    @Mapping(target = "userPassword", source = "password")
    @Mapping(target = "prenom",       source = "prenom")
    @Mapping(target = "telephone",    source = "telephone")
    @Mapping(target = "userKey",      source = "key")
    @Mapping(target = "userVerificationCode", source = "code")
     Users toEntity(RegisterRequest request);

     default Users registerEmployeToEntity(RegisterRequest request) {
        return toEntity(request);
    }

     @Mapping(target = "role", source = "role")
    UserResponse toResponse(Users user);

     // List<UserResponse> toResponseList(List<Users> users);
}