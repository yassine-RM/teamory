package org.teamory.backend.Services.Contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teamory.backend.DTOs.Requests.Create.CreateTeamDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTeamDTO;
import org.teamory.backend.DTOs.Responses.TeamResponseDTO;
import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.Team;
import org.teamory.backend.Entities.Team;

import java.util.List;
import java.util.UUID;

public interface TeamInterface {

    public TeamResponseDTO getTeamById(UUID id);
    public Page<TeamResponseDTO> getAllTeams(Pageable pageable);
    public TeamResponseDTO createTeam(CreateTeamDTO userDTO);
    public void deleteTeam(UUID id);
    public TeamResponseDTO updateTeam(UUID id, UpdateTeamDTO userDTO);

    public void addMemberToTeam(String teamId, String userId);
    public void removeMemberFromTeam(String teamId, String userId);
    public List<Team> getTeamMembers(String teamId);
    public List<Task> getTeamTasks(String teamId);
}
