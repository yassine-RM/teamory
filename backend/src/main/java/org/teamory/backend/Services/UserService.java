package org.teamory.backend.Services;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teamory.backend.DTOs.Requests.Create.CreateUserDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateUserDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Entities.User;
import org.teamory.backend.Mappers.UserMapper;
import org.teamory.backend.Repositories.UserRepository;
import org.teamory.backend.Services.Contracts.UserInterface;

import java.util.UUID;


@Service
@Data
@AllArgsConstructor
public class UserService implements UserInterface {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponseDTO getUserById(UUID id) {
        User user =  userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + id));
        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new EntityNotFoundException("User not found with username: " + username) );
        return userMapper.toDTO(user);
    }

    @Override
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        // return usersPage.map(user -> userMapper.toDTO(user));
        return usersPage.map(userMapper::toDTO);
    }


    @Override
    public UserResponseDTO createUser(CreateUserDTO userDTO) {
        System.out.println("user dto "+userDTO);
        User user = userMapper.toEntity(userDTO);
        System.out.println("user "+user);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public void deleteUser(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO updateUser(UUID id, UpdateUserDTO userDTO) {
        User user = userRepository.findById(id).
                orElseThrow( () -> new EntityNotFoundException("User not found with id: " + id));

        userMapper.updateEntityFromDTO(user, userDTO);
        User updatedUser = userRepository.save(user);

        return userMapper.toDTO(updatedUser);
    }
}
