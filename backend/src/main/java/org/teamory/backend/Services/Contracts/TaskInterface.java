package org.teamory.backend.Services.Contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teamory.backend.DTOs.Requests.Create.CreateTaskDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTaskDTO;
import org.teamory.backend.DTOs.Responses.TaskResponseDTO;
import java.util.UUID;

public interface TaskInterface {

    public TaskResponseDTO getTaskById(UUID taskId);
    TaskResponseDTO updateTask(UUID taskId, UpdateTaskDTO taskDto);
    public TaskResponseDTO createTask(CreateTaskDTO taskDto);
    public Boolean deleteTask(UUID taskId);

    public Page<TaskResponseDTO> getAllTasks(Pageable pageable);
    public String assignTaskToMember(UUID taskId, UUID userId);
    public String unAssignTaskToMember(UUID taskId, UUID userId);
}
