package org.teamory.backend.Mappers;

import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateTeamDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTeamDTO;
import org.teamory.backend.DTOs.Responses.TeamResponseDTO;
import org.teamory.backend.Entities.Team;

@Component
public class TeamMapper {

    public TeamResponseDTO toDTO(Team team) {
        TeamResponseDTO dto = new TeamResponseDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setDescription(team.getDescription());
        return dto;
    }

    public Team toEntity(CreateTeamDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setDescription(dto.getDescription());
        return team;
    }

    public void updateEntityFromDTO(Team team, UpdateTeamDTO dto) {
        team.setName(dto.getName());
        team.setDescription(dto.getDescription()    );
    }
}
