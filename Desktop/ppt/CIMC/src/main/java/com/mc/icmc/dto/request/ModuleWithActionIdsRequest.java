package com.mc.icmc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleWithActionIdsRequest {
    private Long moduleId;
    private List<Long> actionIds;

}
