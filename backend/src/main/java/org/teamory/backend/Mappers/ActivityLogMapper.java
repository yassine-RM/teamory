package org.teamory.backend.Mappers;


import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateActivityLogDTO;
import org.teamory.backend.Entities.ActivityLog;

@Component
public class ActivityLogMapper {

    public CreateActivityLogDTO toDTO(ActivityLog log) {
        CreateActivityLogDTO dto = new CreateActivityLogDTO();
        dto.setAction(log.getAction());
        dto.setDetails(log.getDetails());
        dto.setTimestamp(log.getTimestamp());

        return dto;
    }

    public ActivityLog toEntity(CreateActivityLogDTO dto) {
        ActivityLog log = new ActivityLog();
        log.setAction(dto.getAction());
        log.setDetails(dto.getDetails());
        log.setTimestamp(dto.getTimestamp());

        return log;
    }

}
