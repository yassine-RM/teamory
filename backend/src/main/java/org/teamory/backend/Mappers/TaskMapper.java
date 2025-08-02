package org.teamory.backend.Mappers;

import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateTaskDTO;
import org.teamory.backend.DTOs.Requests.Create.CreateTaskDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTaskDTO;
import org.teamory.backend.DTOs.Responses.TaskResponseDTO;
import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.Task;

@Component
public class TaskMapper {

    public TaskResponseDTO toDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        return dto;
    }

    public Task toEntity(CreateTaskDTO dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        return task;
    }

    public void updateEntityFromDTO(Task task, UpdateTaskDTO dto) {
        task.setName(dto.getName());
        task.setStatus(dto.getStatus());
        task.setDescription(dto.getDescription());
    }
}
