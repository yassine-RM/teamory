package org.teamory.backend.Controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamory.backend.DTOs.Requests.Create.CreateTeamDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTeamDTO;
import org.teamory.backend.DTOs.Responses.TaskResponseDTO;
import org.teamory.backend.DTOs.Responses.TeamResponseDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Services.Contracts.TeamInterface;

import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
@Data
@CrossOrigin(origins = "*")
@Tag(name = "Team Management", description = "APIs for managing teams")
public class TeamController {

    private final TeamInterface teamService;

    @Operation(summary = "Get all teams", description = "Returns all teams")
    @GetMapping
    public ResponseEntity<Page<TeamResponseDTO>> all(Pageable pageable){
        Page<TeamResponseDTO> teams =  teamService.getAllTeams(pageable);
        return ResponseEntity.ok(teams);
    }

    @Operation(summary = "Get team by id", description = "Returns team by id")
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> show(@PathVariable UUID id) {
        TeamResponseDTO teamDto = teamService.getTeamById(id);

        if (teamDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamDto);
    }

    @Operation(summary = "Create team", description = "Creates a new team")
    @PostMapping
    public ResponseEntity<TeamResponseDTO> create(@RequestBody @Valid CreateTeamDTO teamDTO){
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @Operation(summary = "Update team by id", description = "Updates team by id")
    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateTeamDTO teamDTO
    ){

        TeamResponseDTO team = teamService.updateTeam(id, teamDTO);
        return ResponseEntity.ok(team);

    }

    @Operation(summary = "Delete team by id", description = "Deletes team by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        teamService.deleteTeam(id);

        return ResponseEntity.ok("Team deleted successfully");
    }

    @Operation(summary = "Get team members", description = "Returns team members by team id")
    @GetMapping("/{id}/members")
    public ResponseEntity<Page<UserResponseDTO>> getTeamMembers(@PathVariable UUID id, Pageable pageable){
        Page<UserResponseDTO> members =  teamService.getTeamMembers(id, pageable);
        return ResponseEntity.ok(members);
    }

    @Operation(summary = "Get team tasks", description = "Returns team tasks by team id")
    @GetMapping("/{id}/tasks")
    public ResponseEntity<Page<TaskResponseDTO>> getTeamTasks(@PathVariable UUID id, Pageable pageable){
        Page<TaskResponseDTO> tasks =  teamService.getTeamTasks(id, pageable);
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Add member to a team", description = "Adds a member to a team")
    @PostMapping("/{teamId}/{memberId}")
    public void addMemberToTeam(@PathVariable UUID teamId, @PathVariable UUID memberId){
        teamService.addMemberToTeam(teamId, memberId);
    }

    @Operation(summary = "Remove member from a team", description = "Removes a member from a team")
    @DeleteMapping("/{teamId}/{memberId}")
    public void removeMemberFromTeam(@PathVariable UUID teamId, @PathVariable UUID memberId){
        teamService.removeMemberFromTeam(teamId, memberId);
    }

}