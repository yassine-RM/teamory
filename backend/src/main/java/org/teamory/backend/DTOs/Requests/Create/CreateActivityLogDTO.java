package org.teamory.backend.DTOs.Requests.Create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateActivityLogDTO {

    @NotNull
    private String action;
    @NotNull
    private String details;

}
