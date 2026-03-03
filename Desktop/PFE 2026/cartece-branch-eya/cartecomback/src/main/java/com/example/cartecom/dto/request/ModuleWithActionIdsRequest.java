package com.example.cartecom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * DTO representing a Module with associated Action IDs
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleWithActionIdsRequest {
    private Long moduleId;
    private List<Long> actionIds;
}