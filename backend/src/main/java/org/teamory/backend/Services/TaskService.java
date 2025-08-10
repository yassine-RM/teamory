package org.teamory.backend.Services;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamory.backend.DTOs.Requests.Create.CreateTaskDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTaskDTO;
import org.teamory.backend.DTOs.Responses.TaskResponseDTO;
import org.teamory.backend.Entities.ActivityLog;
import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.User;
import org.teamory.backend.Enums.ActivityLogType;
import org.teamory.backend.Mappers.TaskMapper;
import org.teamory.backend.Repositories.TaskRepository;
import org.teamory.backend.Repositories.UserRepository;
import org.teamory.backend.Services.Contracts.TaskInterface;

import java.time.LocalDateTime;
import java.util.UUID;


@Transactional
@Service
@Data
@AllArgsConstructor
public class TaskService implements TaskInterface {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ActivityLogService activityLogService;
    private final TaskMapper taskMapper;


    @Override
    public TaskResponseDTO getTaskById(UUID taskId) {
        Task task =  taskRepository.findById(taskId)
                .orElseThrow( () -> new EntityNotFoundException("Task not found with id: " + taskId));
        return taskMapper.toDTO(task);
    }

    @Override
    @PreAuthorize( "hasRole('ADMIN')")
    public TaskResponseDTO updateTask(UUID taskId, UpdateTaskDTO taskDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow( () -> new EntityNotFoundException("Task not found with id: " + taskId));

        taskMapper.updateEntityFromDTO(task, taskDto);
        Task updatedTask = taskRepository.save(task);

        ActivityLog activityLog = new ActivityLog();
        activityLog.setAction(ActivityLogType.UPDATE_TASK);
        activityLog.setDetails("Task "+taskId+" has been updated.");
        activityLog.setTimestamp(LocalDateTime.now());
        activityLog.setPerformedBy(updatedTask.getAssignedTo());
        activityLog.setRelatedTask(updatedTask);
        activityLogService.createActivityLog(activityLog);

        return taskMapper.toDTO(updatedTask);
    }

    @Override
    public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {
        Page<Task> tasksPage = taskRepository.findAll(pageable);
        return tasksPage.map(taskMapper::toDTO);
    }

    @Override
    public TaskResponseDTO createTask(CreateTaskDTO taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task savedTask = taskRepository.save(task);

        ActivityLog activityLog = new ActivityLog();
        activityLog.setAction(ActivityLogType.CREATE_TASK);
        activityLog.setDetails("Task "+taskDto.getName()+" has been created.");
        activityLog.setTimestamp(LocalDateTime.now());
        activityLog.setPerformedBy(task.getAssignedTo());
        activityLog.setRelatedTask(task);
        activityLogService.createActivityLog(activityLog);

        return taskMapper.toDTO(savedTask);
    }

    @Override
    public Boolean deleteTask(UUID taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow( () -> new EntityNotFoundException("Task not found with id: " + taskId));
        taskRepository.delete(task);

        ActivityLog activityLog = new ActivityLog();
        activityLog.setAction(ActivityLogType.DELETE_TASK);
        activityLog.setDetails("Task "+task.getName()+" has been removed.");
        activityLog.setTimestamp(LocalDateTime.now());
        activityLog.setPerformedBy(task.getAssignedTo());
        activityLog.setRelatedTask(task);
        activityLogService.createActivityLog(activityLog);

        return true;
    }

    @Override
    public String assignTaskToMember(UUID taskId, UUID userId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow( () -> new EntityNotFoundException("Task not found with id: " + taskId));
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + userId));

        if (task.getAssignedTo() != null && task.getAssignedTo().getId().equals(userId)) {
            throw new IllegalArgumentException("The task is already assigned to the user.");
        }

        task.setAssignedTo(user);

        ActivityLog activityLog = new ActivityLog();
        activityLog.setAction(ActivityLogType.ASSIGN_TASK);
        activityLog.setDetails("Task "+task.getName()+" assigned to user "+user.getUsername());
        activityLog.setTimestamp(LocalDateTime.now());
        activityLog.setPerformedBy(task.getAssignedTo());
        activityLog.setRelatedTask(task);
        activityLogService.createActivityLog(activityLog);

        return "The task "+taskId+" has been assigned to the user "+userId;
    }

    @Override
    public String unAssignTaskToMember(UUID taskId, UUID userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow( () -> new EntityNotFoundException("Task not found with id: " + taskId));
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + userId));

        if (!user.getTasks().contains(task)) {
            throw new IllegalArgumentException("The task is not assigned to the user.");
        }
        user.getTasks().remove(task);

        ActivityLog activityLog = new ActivityLog();
        activityLog.setAction(ActivityLogType.UNASSIGN_TASK);
        activityLog.setDetails("Task "+task.getName()+" unassigned from user "+user.getUsername());
        activityLog.setTimestamp(LocalDateTime.now());
        activityLog.setPerformedBy(task.getAssignedTo());
        activityLog.setRelatedTask(task);
        activityLogService.createActivityLog(activityLog);

        return "The task "+taskId+" has been unassigned from the user "+userId;
    }
}
