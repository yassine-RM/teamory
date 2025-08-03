package org.teamory.backend.Mappers;

import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateTaskDTO;
import org.teamory.backend.DTOs.Requests.Create.CreateTaskDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTaskDTO;
import org.teamory.backend.DTOs.Responses.TaskResponseDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.Task;

import java.time.LocalDateTime;

@Component
public class TaskMapper {

    private final UserMapper userMapper;

    public TaskMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public TaskResponseDTO toDTO(Task task) {
        if (task == null) return null;

        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId() != null ? task.getId() : null);
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());

        if (task.getAssignedTo() != null){
            UserResponseDTO userDTO = userMapper.toDTO(task.getAssignedTo());
            dto.setAssignedTo(userDTO);
        }

        return dto;
    }


    public Task toEntity(CreateTaskDTO dto) {
        Task task = new Task();

        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());
        task.setCreatedAt(LocalDateTime.now());

        return task;
    }

    public void updateEntityFromDTO(Task task, UpdateTaskDTO dto) {
        if (dto.getName() != null && !dto.getName().trim().isEmpty() )
            task.setName(dto.getName());

        if (dto.getDescription() != null)
            task.setDescription(dto.getDescription());

        if (dto.getStatus() != null)
            task.setStatus(dto.getStatus());

        if (dto.getDueDate() != null )
            task.setDueDate(dto.getDueDate());
        
    }
}
