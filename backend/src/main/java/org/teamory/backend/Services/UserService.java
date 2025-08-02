package org.teamory.backend.Services;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teamory.backend.DTOs.Requests.Create.CreateUserDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Entities.User;
import org.teamory.backend.Mappers.UserMapper;
import org.teamory.backend.Repositories.UserRepository;
import org.teamory.backend.Services.Contracts.UserInterface;


@Service
@Data
@AllArgsConstructor
public class UserService implements UserInterface {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponseDTO getUserById(String id) {
        User user =  userRepository.findById(id).orElse(null);
        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
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
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
