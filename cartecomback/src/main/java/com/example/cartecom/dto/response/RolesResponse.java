package com.example.cartecom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesResponse {
    private Long id;
    private String roleName;
    private List<ModulesResponse> modules;
}