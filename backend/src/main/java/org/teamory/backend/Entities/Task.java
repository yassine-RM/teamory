package org.teamory.backend.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teamory.backend.Enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Comment> comments;

}
