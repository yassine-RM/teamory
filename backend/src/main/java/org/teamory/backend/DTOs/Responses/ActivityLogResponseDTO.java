package org.teamory.backend.DTOs.Responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ActivityLogResponseDTO {

    private UUID id;
    private String action;
    private String details;
    private LocalDateTime timestamp;

}
