package org.teamory.backend.Services;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamory.backend.DTOs.Requests.Create.CreateCommentDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateCommentDTO;
import org.teamory.backend.DTOs.Responses.ActivityLogResponseDTO;
import org.teamory.backend.DTOs.Responses.CommentResponseDTO;
import org.teamory.backend.Entities.ActivityLog;
import org.teamory.backend.Entities.Comment;
import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.User;
import org.teamory.backend.Mappers.ActivityLogMapper;
import org.teamory.backend.Mappers.CommentMapper;
import org.teamory.backend.Mappers.TaskMapper;
import org.teamory.backend.Repositories.ActivityLogRepository;
import org.teamory.backend.Repositories.CommentRepository;
import org.teamory.backend.Repositories.TaskRepository;
import org.teamory.backend.Repositories.UserRepository;
import org.teamory.backend.Services.Contracts.ActivityLogInterface;
import org.teamory.backend.Services.Contracts.CommentInterface;

import java.util.List;
import java.util.UUID;


@Transactional
@Service
@Data
@AllArgsConstructor
public class ActivityLogService implements ActivityLogInterface {

    private final ActivityLogRepository activityLogRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final ActivityLogMapper activityLogMapper;


    @Override
    public ActivityLogResponseDTO createActivityLog(ActivityLog activityLog) {
        ActivityLog savedActivityLog = activityLogRepository.save(activityLog);
        return activityLogMapper.toDTO(savedActivityLog);
    }

    @Override
    public Boolean clearTaskHistory(UUID taskId) {
        taskRepository.findById(taskId)
                .orElseThrow( () -> new EntityNotFoundException("Task not found with id: " + taskId));

        List<ActivityLog> activityLogs = activityLogRepository
                .findByRelatedTaskId(taskId);

        activityLogRepository.deleteAll(activityLogs);

        return true;
    }

    @Override
    public Boolean clearUserHistory(UUID userId) {
        userRepository.findById(userId)
                .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + userId));

        List<ActivityLog> activityLogs = activityLogRepository
                .findByPerformedById(userId);

        activityLogRepository.deleteAll(activityLogs);

        return true;
    }

    @Override
    public List<ActivityLogResponseDTO> getTaskHistory(UUID taskId) {
        List<ActivityLog> activityLogs = activityLogRepository.findByRelatedTaskId(taskId);

        return activityLogs.stream().map(activityLogMapper::toDTO).toList();
    }

    @Override
    public List<ActivityLogResponseDTO> getUserHistory(UUID userId) {
        List<ActivityLog> activityLogs = activityLogRepository.findByPerformedById(userId);

        return activityLogs.stream().map(activityLogMapper::toDTO).toList();
    }
}
