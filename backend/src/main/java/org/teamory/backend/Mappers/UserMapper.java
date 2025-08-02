package org.teamory.backend.Mappers;

import org.springframework.stereotype.Component;
import org.teamory.backend.DTOs.Requests.Create.CreateUserDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateUserDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Entities.User;

@Component
public class UserMapper {

    public UserResponseDTO toDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User toEntity(CreateUserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public void updateEntityFromDTO(User user, UpdateUserDTO dto) {
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
    }
}

