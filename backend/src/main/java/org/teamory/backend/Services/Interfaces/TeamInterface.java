package org.teamory.backend.Services.Interfaces;

import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.Team;
import org.teamory.backend.Entities.User;

import java.util.List;
import java.util.UUID;

public interface TeamInterface {

    public Team getTeamById(UUID teamId);
    public List<Team> getAllTeams();
    public void createTeam(Team team);
    public void deleteTeam(Team team);
    public void updateTeam(Team team);
    public void addMemberToTeam(String teamId, String userId);
    public void removeMemberFromTeam(String teamId, String userId);
    public List<User> getTeamMembers(String teamId);
    public List<Task> getTeamTasks(String teamId);
}
