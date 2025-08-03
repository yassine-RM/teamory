package org.teamory.backend.Services.Contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teamory.backend.DTOs.Requests.Create.CreateCommentDTO;
import org.teamory.backend.DTOs.Requests.Create.CreateUserDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateCommentDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateUserDTO;
import org.teamory.backend.DTOs.Responses.CommentResponseDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;

import java.util.UUID;

public interface CommentInterface {

    public CommentResponseDTO addCommentToTask(UUID taskId, UUID userId, CreateCommentDTO commentDTO);
    public Page<CommentResponseDTO> getCommentsByTaskId(UUID taskId, Pageable pageable);
    public void deleteCommentFromTask(UUID taskId, UUID commentId);
    public CommentResponseDTO updateComment(UUID commentId, UpdateCommentDTO commentDTO);
    public CommentResponseDTO getCommentById(UUID commentId);
}
