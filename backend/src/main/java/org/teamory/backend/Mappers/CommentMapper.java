package org.teamory.backend.Mappers;


import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateCommentDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateCommentDTO;
import org.teamory.backend.DTOs.Responses.CommentResponseDTO;
import org.teamory.backend.Entities.Comment;

@Component
public class CommentMapper {

    public CommentResponseDTO toDTO(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }

    public Comment toEntity(CreateCommentDTO dto) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(dto.getCreatedAt());
        return comment;
    }

    public void updateEntityFromDTO(Comment comment, UpdateCommentDTO dto) {
        comment.setContent(dto.getContent());
    }

}
