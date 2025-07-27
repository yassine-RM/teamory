package org.teamory.backend.Services;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.teamory.backend.Entities.User;
import org.teamory.backend.Repositories.UserRepository;
import org.teamory.backend.Services.Interfaces.UserInterface;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class UserService implements UserInterface {

    private final UserRepository userRepository;


    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public void createIUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
