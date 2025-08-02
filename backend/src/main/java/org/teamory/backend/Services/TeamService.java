package org.teamory.backend.Services;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamory.backend.DTOs.Requests.Create.CreateTeamDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTeamDTO;
import org.teamory.backend.DTOs.Responses.TaskResponseDTO;
import org.teamory.backend.DTOs.Responses.TeamResponseDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.Team;
import org.teamory.backend.Entities.User;
import org.teamory.backend.Mappers.TaskMapper;
import org.teamory.backend.Mappers.TeamMapper;
import org.teamory.backend.Mappers.UserMapper;
import org.teamory.backend.Repositories.TaskRepository;
import org.teamory.backend.Repositories.TeamRepository;
import org.teamory.backend.Repositories.UserRepository;
import org.teamory.backend.Services.Contracts.TeamInterface;

import java.util.UUID;

@Transactional
@Service
@Data
@AllArgsConstructor
public class TeamService implements TeamInterface {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TeamMapper teamMapper;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;


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
    public void addMemberToTeam(UUID teamId, UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()-> new EntityNotFoundException("Team not found with id: " + teamId));

        team.getMembers().add(user);

    }

    @Override
    public void removeMemberFromTeam(UUID teamId, UUID userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(()-> new EntityNotFoundException("Team not found with id: " + teamId));

        team.getMembers().removeIf(member -> member.getId().equals(userId));

    }

    @Override
    public Page<UserResponseDTO> getTeamMembers(UUID teamId, Pageable pageable) {
        if (!teamRepository.existsById(teamId)) {
            throw new EntityNotFoundException("Team not found with id: " + teamId);
        }

        Page<User> members = userRepository.findAllByTeamId(teamId, pageable);
        return members.map(userMapper::toDTO);
    }



    @Override
    public Page<TaskResponseDTO> getTeamTasks(UUID teamId, Pageable pageable) {
        Page<Task> tasks =  taskRepository.findByTeamId(teamId, pageable);
        return tasks.map(taskMapper::toDTO);
    }
}
