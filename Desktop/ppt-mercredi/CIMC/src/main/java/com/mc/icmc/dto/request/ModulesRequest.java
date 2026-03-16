package com.mc.icmc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModulesRequest {

    @NotBlank
    @Size(max = 100)
    private String moduleName;


}
