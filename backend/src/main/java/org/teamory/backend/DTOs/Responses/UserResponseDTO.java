package org.teamory.backend.DTOs.Responses;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {

    private UUID id;
    private String username;
    private String fullName;
     private String email;

}
