package org.teamory.backend.Controllers;


import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamory.backend.DTOs.Requests.Create.CreateTeamDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTeamDTO;
import org.teamory.backend.DTOs.Responses.TeamResponseDTO;
import org.teamory.backend.Services.Contracts.TeamInterface;

import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
@Data
public class TeamController {

    private final TeamInterface teamService;

    @GetMapping
    public ResponseEntity<Page<TeamResponseDTO>> all(Pageable pageable){
        Page<TeamResponseDTO> teams =  teamService.getAllTeams(pageable);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> show(@PathVariable UUID id) {
        TeamResponseDTO teamDto = teamService.getTeamById(id);

        if (teamDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamDto);
    }

    @PostMapping
    public ResponseEntity<TeamResponseDTO> create(@RequestBody @Valid CreateTeamDTO teamDTO){
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateTeamDTO teamDTO
    ){

        TeamResponseDTO team = teamService.updateTeam(id, teamDTO);
        return ResponseEntity.ok(team);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        teamService.deleteTeam(id);

        return ResponseEntity.ok("Team deleted successfully");
    }

}