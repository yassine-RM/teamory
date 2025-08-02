package org.teamory.backend.Mappers;

import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateTeamDTO;
import org.teamory.backend.Entities.Team;

@Component
public class TeamMapper {

    public CreateTeamDTO toDTO(Team team) {
        CreateTeamDTO dto = new CreateTeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setDescription(team.getDescription());
        return dto;
    }

    public Team toEntity(CreateTeamDTO dto) {
        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        team.setDescription(dto.getDescription());
        return team;
    }
}
