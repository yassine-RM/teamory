package org.teamory.backend.DTOs.Requests.Create;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateActivityLogDTO {

    private UUID id;
    private String action;
    private String details;
    private LocalDateTime timestamp;

}
