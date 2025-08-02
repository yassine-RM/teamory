package org.teamory.backend.DTOs.Requests.Create;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateCommentDTO {

    private UUID id;
    private String content;
    private LocalDateTime createdAt;
}
