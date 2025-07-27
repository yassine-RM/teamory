package org.teamory.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamory.backend.Entities.Team;
import org.teamory.backend.Entities.User;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
}
