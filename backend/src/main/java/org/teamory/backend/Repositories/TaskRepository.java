package org.teamory.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamory.backend.Entities.Task;
import org.teamory.backend.Entities.Team;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
