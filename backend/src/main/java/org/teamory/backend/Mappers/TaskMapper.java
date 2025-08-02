package org.teamory.backend.Mappers;

import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateTaskDTO;
import org.teamory.backend.Entities.Task;

@Component
public class TaskMapper {

    public CreateTaskDTO toDTO(Task task) {
        CreateTaskDTO dto = new CreateTaskDTO();
        dto.setName(task.getName());
        dto.setStatus(task.getStatus());
        dto.setDescription(task.getDescription());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setDescription(task.getDescription());
        return dto;
    }

    public Task toEntity(CreateTaskDTO dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setStatus(dto.getStatus());
        task.setDescription(dto.getDescription());
        task.setCreatedAt(dto.getCreatedAt());
        task.setDescription(dto.getDescription());
        return task;
    }
}
