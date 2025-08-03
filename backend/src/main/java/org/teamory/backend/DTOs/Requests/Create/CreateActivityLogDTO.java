package org.teamory.backend.DTOs.Requests.Create;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateActivityLogDTO {

    private String action;
    private String details;
    private LocalDateTime timestamp;

}
