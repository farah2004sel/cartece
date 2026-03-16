package com.mc.icmc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogUserRequest {

    private Long userId;   // ID de l'utilisateur lié au log
    private Long moduleId; // ID du module
    private Long actionId; // ID de l'action

    @NotBlank
    @Size(max = 255)
    private String logUserTitle;

//    @Size(max = 50)
//    private String logUserIp;
//
//    @Size(max = 100)
//    private String macVendeur;

    @Size(max = 255)
    private String logUserAgent;

    private String logUserAgentString;
}
