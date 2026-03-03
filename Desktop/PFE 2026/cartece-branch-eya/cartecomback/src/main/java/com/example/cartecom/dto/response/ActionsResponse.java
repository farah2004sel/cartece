package com.example.cartecom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionsResponse {
    private Long id;
    private String actionName;
    private String description;
}