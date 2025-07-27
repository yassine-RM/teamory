package org.teamory.backend.Services.Interfaces;

import org.teamory.backend.Entities.User;

import java.util.List;

public interface UserInterface {

    public User getUserById(String id);
    public User getUserByUsername(String username);
    public List<User> getAllUsers();
    public void createIUser(User user);
    public void deleteUser(User user);
    public void updateUser(User user);

}
