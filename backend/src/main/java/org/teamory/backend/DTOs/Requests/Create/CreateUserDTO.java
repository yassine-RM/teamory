package org.teamory.backend.DTOs.Requests.Create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    private String email;

}
