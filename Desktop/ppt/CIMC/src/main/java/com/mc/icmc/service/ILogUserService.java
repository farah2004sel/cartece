package com.mc.icmc.service;

import com.mc.icmc.dto.request.LogUserRequest;
import com.mc.icmc.dto.response.LogUserResponse;

import java.util.List;
import java.util.UUID;

public interface ILogUserService {

    LogUserResponse createLogUser(LogUserRequest request, String clientIp);

    LogUserResponse getLogUserByUuid(UUID uuid);

    List<LogUserResponse> getAllLogUsers();

    void deleteLogUser(UUID uuid);
}
