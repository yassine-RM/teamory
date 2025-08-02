package org.teamory.backend.DTOs.Responses;

import lombok.Data;

import java.util.UUID;

@Data
public class TeamResponseDTO {

    private UUID id;
    private String name;
    private String description;
}
