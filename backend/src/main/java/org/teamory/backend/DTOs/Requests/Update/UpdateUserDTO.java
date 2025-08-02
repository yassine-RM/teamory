package org.teamory.backend.DTOs.Requests.Update;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserDTO {

    private String username;
    private String fullName;
    private String email;

}
