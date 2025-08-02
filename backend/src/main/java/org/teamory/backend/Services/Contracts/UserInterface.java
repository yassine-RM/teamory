package org.teamory.backend.Services.Contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teamory.backend.DTOs.Requests.Create.CreateUserDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Entities.User;

public interface UserInterface {

    public UserResponseDTO getUserById(String id);
    public UserResponseDTO getUserByUsername(String username);
    public Page<UserResponseDTO> getAllUsers(Pageable pageable);
    public UserResponseDTO createUser(CreateUserDTO userDTO);
    public void deleteUser(User user);
    public void updateUser(User user);

}
