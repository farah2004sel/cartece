package com.example.cartecom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModulesResponse {
    private Long id;
    private String moduleName;
    private List<ActionsResponse> actions;
}