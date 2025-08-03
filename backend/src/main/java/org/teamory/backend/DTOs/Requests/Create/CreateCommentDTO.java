package org.teamory.backend.DTOs.Requests.Create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCommentDTO {

    @NotNull
    private String content;
}
