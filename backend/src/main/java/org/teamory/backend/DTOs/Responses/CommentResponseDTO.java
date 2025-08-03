package org.teamory.backend.DTOs.Responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentResponseDTO {

    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private UserResponseDTO author;
}
