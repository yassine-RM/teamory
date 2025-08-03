package org.teamory.backend.DTOs.Requests.Update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.teamory.backend.Validations.UniqueEmail;

import java.util.UUID;

@Data
public class UpdateUserDTO {

    private String fullName;
    private String email;

}
