package com.mc.icmc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionsRequest {

    @NotBlank
    @Size(max = 100)
    private String actionTitle;

}
