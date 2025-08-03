package org.teamory.backend.Mappers;


import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Responses.ActivityLogResponseDTO;
import org.teamory.backend.Entities.ActivityLog;

import java.time.LocalDateTime;

@Component
public class ActivityLogMapper {

    public ActivityLogResponseDTO toDTO(ActivityLog log) {
        ActivityLogResponseDTO dto = new ActivityLogResponseDTO();

        dto.setAction(log.getAction());
        dto.setDetails(log.getDetails());

        return dto;
    }

}
