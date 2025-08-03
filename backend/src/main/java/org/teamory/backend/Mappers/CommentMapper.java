package org.teamory.backend.Mappers;


import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateCommentDTO;
import org.teamory.backend.Entities.Comment;

@Component
public class CommentMapper {

    public CreateCommentDTO toDTO(Comment comment) {
        CreateCommentDTO dto = new CreateCommentDTO();
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

}
