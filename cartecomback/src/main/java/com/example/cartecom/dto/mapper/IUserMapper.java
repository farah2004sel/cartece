package com.example.cartecom.dto.mapper;

import com.example.cartecom.domain.Users;
import com.example.cartecom.domain.Roles;
import com.example.cartecom.dto.request.RegisterRequest;
import com.example.cartecom.dto.response.UserResponse;
import com.example.cartecom.dto.response.RolesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Mapping(target = "userName", source = "nom")
    @Mapping(target = "userEmail", source = "email")
    @Mapping(target = "userPassword", source = "password")
    @Mapping(target = "userVerificationCode", ignore = true)
    @Mapping(target = "userEnabled", ignore = true)
    @Mapping(target = "role", ignore = true) // ← IMPORTANT
    @Mapping(target = "userFailedLoginAttempts", ignore = true)
    @Mapping(target = "userLockUntil", ignore = true)
    @Mapping(target = "userCancel", ignore = true)
    @Mapping(target = "id", ignore = true)
    Users toEntity(RegisterRequest request);

     UserResponse toResponse(Users user);
    RolesResponse toRolesResponse(Roles role);


     // List<UserResponse> toResponseList(List<Users> users);
}