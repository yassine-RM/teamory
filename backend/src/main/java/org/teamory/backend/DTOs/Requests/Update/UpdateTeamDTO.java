package org.teamory.backend.DTOs.Requests.Update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTeamDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

}
