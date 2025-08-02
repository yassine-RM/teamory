package org.teamory.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamory.backend.Entities.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
}
