package org.teamory.backend.DTOs.Requests.Create;

import lombok.Data;
import org.teamory.backend.Enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateTaskDTO {

    private String name;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
}
