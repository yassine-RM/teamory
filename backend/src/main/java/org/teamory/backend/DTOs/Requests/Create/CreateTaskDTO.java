package org.teamory.backend.DTOs.Requests.Create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.teamory.backend.Enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateTaskDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private TaskStatus status;
    @NotNull
    private LocalDate dueDate;
}
