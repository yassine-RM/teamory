package org.teamory.backend.DTOs.Requests.Create;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTeamDTO {

    private UUID id;
    private String name;
    private String description;
}
