package org.teamory.backend.Services;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamory.backend.DTOs.Requests.Create.CreateCommentDTO;
import org.teamory.backend.DTOs.Requests.Create.CreateTaskDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateCommentDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTaskDTO;
import org.teamory.backend.DTOs.Responses.CommentResponseDTO;
import org.teamory.backend.DTOs.Responses.TaskResponseDTO;
import org.teamory.backend.Entities.Comment;
import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.User;
import org.teamory.backend.Mappers.CommentMapper;
import org.teamory.backend.Mappers.TaskMapper;
import org.teamory.backend.Repositories.CommentRepository;
import org.teamory.backend.Repositories.TaskRepository;
import org.teamory.backend.Repositories.UserRepository;
import org.teamory.backend.Services.Contracts.CommentInterface;
import org.teamory.backend.Services.Contracts.TaskInterface;

import java.util.UUID;


@Transactional
@Service
@Data
@AllArgsConstructor
public class CommentService implements CommentInterface {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final TaskMapper taskMapper;
    private final CommentMapper commentMapper;


    @Override
    public CommentResponseDTO addCommentToTask(UUID taskId, UUID userId, CreateCommentDTO commentDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow( () -> new EntityNotFoundException("Task not found with id: " + taskId));
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + userId));

        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setTask(task);
        comment.setAuthor(user);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDTO(savedComment);
    }

    @Override
    public Page<CommentResponseDTO> getCommentsByTaskId(UUID taskId, Pageable pageable) {
        Page<Comment> commentPage =  commentRepository.findByTaskId(taskId, pageable);
        return commentPage.map(commentMapper::toDTO);
    }

    @Override
    public Boolean deleteCommentFromTask(UUID taskId, UUID commentId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

        if (!comment.getTask().getId().equals(taskId)) {
            throw new IllegalArgumentException("The comment is not associated with the task.");
        }

        task.getComments().remove(comment);

        commentRepository.delete(comment);

        return true;
    }


    @Override
    public CommentResponseDTO updateComment(UUID commentId, UpdateCommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow( () -> new EntityNotFoundException("Comment not found with id: " + commentId));

        commentMapper.updateEntityFromDTO(comment, commentDTO);
        Comment updatedComment  = commentRepository.save(comment);
        return commentMapper.toDTO(updatedComment);
    }

    @Override
    public CommentResponseDTO getCommentById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow( () -> new EntityNotFoundException("Comment not found with id: " + commentId));

        return commentMapper.toDTO(comment);
    }
}
