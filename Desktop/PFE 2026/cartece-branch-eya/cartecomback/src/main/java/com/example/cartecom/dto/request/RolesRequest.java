package com.example.cartecom.dto.request;

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

    private Long directionId;
    private String description;

     private List<ModuleWithActionIdsRequest> modules;
}
