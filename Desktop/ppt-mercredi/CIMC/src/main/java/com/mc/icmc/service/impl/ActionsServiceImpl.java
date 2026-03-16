package com.mc.icmc.service.impl;

import com.mc.icmc.domain.Actions;
import com.mc.icmc.dto.mapper.IActionsMapper;
import com.mc.icmc.dto.request.ActionsRequest;
import com.mc.icmc.dto.request.LogUserRequest;
import com.mc.icmc.dto.response.ActionsResponse;
import com.mc.icmc.error.exception.ActionAlreadyExistsException;
import com.mc.icmc.error.exception.ActionNotFoundException;
import com.mc.icmc.repository.IActionsRepository;
import com.mc.icmc.service.IActionsService;
import com.mc.icmc.service.ILogUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ActionsServiceImpl implements IActionsService {

    private final IActionsRepository actionsRepository;
    private final IActionsMapper actionsMapper;
    private final ILogUserService logUserService;

    @Override
    public ActionsResponse createAction(ActionsRequest request) {
        // Vérifier si un actionTitle existe deja
        if (actionsRepository.existsByActionTitle(request.getActionTitle())) {
            throw new ActionAlreadyExistsException(request.getActionTitle());
        }

        Actions action = actionsMapper.toEntity(request);
        action = actionsRepository.save(action);

        return actionsMapper.toResponse(action);
    }


    @Override
    public ActionsResponse updateAction(Long actionId, ActionsRequest request) {
        Actions action = actionsRepository.findById(actionId)
                .orElseThrow(ActionNotFoundException::new);

        actionsRepository.findByActionTitle(request.getActionTitle())
                .filter(a -> !a.getActionId().equals(actionId))
                .ifPresent(a -> {
                    throw new ActionAlreadyExistsException(request.getActionTitle());
                });

        action.setActionTitle(request.getActionTitle());

        action = actionsRepository.save(action);

        return actionsMapper.toResponse(action);
    }

    @Override
    public ActionsResponse getActionById(Long actionId) {
        Actions action = actionsRepository.findById(actionId)
                .orElseThrow(ActionNotFoundException::new);

        return actionsMapper.toResponse(action);
    }


    @Override
    public List<ActionsResponse> getAllActions() {
        return actionsRepository.findAll()
                .stream()
                .map(actionsMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAction(Long actionId) {
        if (!actionsRepository.existsById(actionId)) {
            throw new ActionNotFoundException();
        }
        actionsRepository.deleteById(actionId);
    }
}
