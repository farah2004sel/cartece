package com.mc.icmc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolesRequest {

    @NotBlank
    @Size(max = 100)
    private String roleName;

//    private Long directionId;


    // Liste des modules existants avec les actions existantes à lier
    private List<ModuleWithActionIdsRequest> modules;
}
