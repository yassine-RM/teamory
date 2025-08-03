package org.teamory.backend.DTOs.Requests.Update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTeamDTO {

    private String name;
    private String description;

}
