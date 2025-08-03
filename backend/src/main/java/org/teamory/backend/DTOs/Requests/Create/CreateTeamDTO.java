package org.teamory.backend.DTOs.Requests.Create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateTeamDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;
}
