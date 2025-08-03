package org.teamory.backend.DTOs.Requests.Create;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCommentDTO {

    private String content;
    private LocalDateTime createdAt;
}
