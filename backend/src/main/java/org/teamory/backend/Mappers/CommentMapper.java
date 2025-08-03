package org.teamory.backend.Mappers;


import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateCommentDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateCommentDTO;
import org.teamory.backend.DTOs.Responses.CommentResponseDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Entities.Comment;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class CommentMapper {

    private final UserMapper userMapper;

    public CommentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CommentResponseDTO toDTO(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());

        if (comment.getAuthor() != null){
            UserResponseDTO userDTO = userMapper.toDTO(comment.getAuthor());
            dto.setAuthor(userDTO);
        }
        return dto;
    }

    public Comment toEntity(CreateCommentDTO dto) {
        Comment comment = new Comment();

        comment.setContent(dto.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        return comment;
    }

    public void updateEntityFromDTO(Comment comment, UpdateCommentDTO dto) {
        if (dto.getContent() != null)
            comment.setContent(dto.getContent());
    }

}
