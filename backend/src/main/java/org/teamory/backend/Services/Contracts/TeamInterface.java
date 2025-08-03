package org.teamory.backend.Services.Contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teamory.backend.DTOs.Requests.Create.CreateTeamDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTeamDTO;
import org.teamory.backend.DTOs.Responses.TaskResponseDTO;
import org.teamory.backend.DTOs.Responses.TeamResponseDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;

import java.util.UUID;

public interface TeamInterface {

    public TeamResponseDTO getTeamById(UUID id);
    public Page<TeamResponseDTO> getAllTeams(Pageable pageable);
    public TeamResponseDTO createTeam(CreateTeamDTO userDTO);
    public void deleteTeam(UUID id);
    public TeamResponseDTO updateTeam(UUID id, UpdateTeamDTO userDTO);

    public void addMemberToTeam(UUID teamId, UUID userId);
    public void removeMemberFromTeam(UUID teamId, UUID userId);
    public Page<UserResponseDTO> getTeamMembers(UUID teamId, Pageable pageable);
    public Page<TaskResponseDTO> getTeamTasks(UUID teamId, Pageable pageable);
}
