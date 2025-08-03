package org.teamory.backend.DTOs.Responses;

import lombok.Data;
import org.teamory.backend.Enums.ActivityLogType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ActivityLogResponseDTO {

    private ActivityLogType action;
    private String details;
    private LocalDateTime timestamp;

}
