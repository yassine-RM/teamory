package org.teamory.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamory.backend.Entities.User;

public interface UserRepository extends JpaRepository<User,String> {
}
