package org.teamory.backend.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamory.backend.Enums.ActivityLogType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "activity_logs")
@Data
@NoArgsConstructor @AllArgsConstructor
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ActivityLogType action;

    private String details;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "performed_by_id")
    private User performedBy;

    @ManyToOne
    @JoinColumn(name = "related_task_id")
    private Task relatedTask;

}
