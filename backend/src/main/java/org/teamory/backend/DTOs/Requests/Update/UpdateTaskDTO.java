package org.teamory.backend.DTOs.Requests.Update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.teamory.backend.Enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UpdateTaskDTO {

    private String name;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;

}
