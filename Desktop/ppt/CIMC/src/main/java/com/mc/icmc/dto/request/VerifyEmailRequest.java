package com.mc.icmc.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyEmailRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String code;
}