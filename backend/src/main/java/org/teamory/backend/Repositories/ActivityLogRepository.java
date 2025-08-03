package org.teamory.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamory.backend.Entities.ActivityLog;

import java.util.List;
import java.util.UUID;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, UUID> {
    List<ActivityLog> findByRelatedTaskId(UUID relatedTaskId);

    List<ActivityLog> findByPerformedById(UUID userId);
}
