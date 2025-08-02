package org.teamory.backend.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;
    private String fullName;
    private String email;

    @ManyToMany(mappedBy = "members")
    private List<Team> teams;

    @OneToMany(mappedBy = "assignedTo")
    private List<Task> tasks;
}
