package com.mc.icmc.service.impl;

import com.mc.icmc.domain.Log_User;
import com.mc.icmc.domain.Modules;
import com.mc.icmc.domain.Actions;
import com.mc.icmc.domain.Users;
import com.mc.icmc.dto.mapper.ILogUserMapper;
import com.mc.icmc.dto.request.LogUserRequest;
import com.mc.icmc.dto.response.LogUserResponse;
import com.mc.icmc.error.exception.ActionNotFoundException;
import com.mc.icmc.error.exception.LogUserNotFoundException;
import com.mc.icmc.error.exception.ModuleNotFoundException;
import com.mc.icmc.error.exception.UserNotFoundException;
import com.mc.icmc.repository.IActionsRepository;
import com.mc.icmc.repository.ILogUserRepository;
import com.mc.icmc.repository.IModulesRepository;
import com.mc.icmc.repository.IUserRepository;
import com.mc.icmc.service.ILogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogUserServiceImpl implements ILogUserService {

    private final ILogUserRepository logUserRepository;
    private final IUserRepository usersRepository;
    private final IModulesRepository modulesRepository;
    private final IActionsRepository actionsRepository;
    private final ILogUserMapper logUserMapper;

    @Override
    public LogUserResponse createLogUser(LogUserRequest request, String clientIp) {
        // Vérifier l'utilisateur
        Users user = usersRepository.findById(request.getUserId())
                .orElseThrow(UserNotFoundException::new);

        // Vérifier le module si fourni
        Modules module = null;
        if (request.getModuleId() != null) {
            module = modulesRepository.findById(request.getModuleId())
                    .orElseThrow(ModuleNotFoundException::new);
        }

        // Vérifier l'action si fournie
        Actions action = null;
        if (request.getActionId() != null) {
            action = actionsRepository.findById(request.getActionId())
                    .orElseThrow(ActionNotFoundException::new);
        }

        // Mapper la request en entity
        Log_User logUser = logUserMapper.toEntity(request);

        // Associer les entités vérifiées
        logUser.setUser(user);
        logUser.setModule(module);
        logUser.setAction(action);

        // Utilisation de l’IP reçue
        logUser.setLogUserIp(clientIp);


        //  Récupération de la MAC à partir de l’IP
        String macAddress = MacAddressUtil.getMacAddress(clientIp);
        logUser.setMacVendeur(macAddress);

        // Sauvegarder en base
        logUser = logUserRepository.save(logUser);

        return logUserMapper.toResponse(logUser);
    }

    @Override
    public LogUserResponse getLogUserByUuid(UUID uuid) {
        Log_User logUser = logUserRepository.findByLogUserUuid(uuid);
        if (logUser == null) throw new LogUserNotFoundException();
        return logUserMapper.toResponse(logUser);
    }

    @Override
    public List<LogUserResponse> getAllLogUsers() {
        return logUserRepository.findAll()
                .stream()
                .map(logUserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLogUser(UUID uuid) {
        Log_User logUser = logUserRepository.findByLogUserUuid(uuid);
        if (logUser == null) throw new LogUserNotFoundException();
        logUserRepository.delete(logUser);
    }
}