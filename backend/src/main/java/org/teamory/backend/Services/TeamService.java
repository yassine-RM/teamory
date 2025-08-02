package org.teamory.backend.Services;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teamory.backend.DTOs.Requests.Create.CreateTeamDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTeamDTO;
import org.teamory.backend.DTOs.Responses.TeamResponseDTO;
import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.Team;
import org.teamory.backend.Mappers.TeamMapper;
import org.teamory.backend.Repositories.TeamRepository;
import org.teamory.backend.Services.Contracts.TeamInterface;

import java.util.List;
import java.util.UUID;


@Service
@Data
@AllArgsConstructor
public class TeamService implements TeamInterface {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;


    @Override
    public TeamResponseDTO getTeamById(UUID id) {
        Team team =  teamRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Team not found with id: " + id));
        return teamMapper.toDTO(team);
    }

    @Override
    public Page<TeamResponseDTO> getAllTeams(Pageable pageable) {
        Page<Team> teamsPage = teamRepository.findAll(pageable);
        // return teamsPage.map(team -> teamMapper.toDTO(team));
        return teamsPage.map(teamMapper::toDTO);
    }


    @Override
    public TeamResponseDTO createTeam(CreateTeamDTO teamDTO) {
        System.out.println("team dto "+teamDTO);
        Team team = teamMapper.toEntity(teamDTO);
        System.out.println("team "+team);
        Team savedTeam = teamRepository.save(team);
        return teamMapper.toDTO(savedTeam);
    }

    @Override
    public void deleteTeam(UUID id) {

        Team team = teamRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Team not found with id: " + id));
        teamRepository.delete(team);
    }

    @Override
    public TeamResponseDTO updateTeam(UUID id, UpdateTeamDTO teamDTO) {
        Team team = teamRepository.findById(id).
                orElseThrow( () -> new EntityNotFoundException("Team not found with id: " + id));

        teamMapper.updateEntityFromDTO(team, teamDTO);
        Team updatedTeam = teamRepository.save(team);

        return teamMapper.toDTO(updatedTeam);
    }

    @Override
    public void addMemberToTeam(String teamId, String userId) {

    }

    @Override
    public void removeMemberFromTeam(String teamId, String userId) {

    }

    @Override
    public List<Team> getTeamMembers(String teamId) {
        return List.of();
    }

    @Override
    public List<Task> getTeamTasks(String teamId) {
        return List.of();
    }
}
