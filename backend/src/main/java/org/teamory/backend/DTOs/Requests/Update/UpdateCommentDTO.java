package org.teamory.backend.DTOs.Requests.Update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateCommentDTO {

    private String content;
}
