package org.teamory.backend.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "team_members",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members;

    @OneToMany(mappedBy = "team")
    private List<Task> tasks;

}
