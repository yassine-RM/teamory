package org.teamory.backend.DTOs.Requests.Update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.teamory.backend.Enums.TaskStatus;

@Data
public class UpdateTaskDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private TaskStatus status;

}
