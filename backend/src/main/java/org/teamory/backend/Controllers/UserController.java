package org.teamory.backend.Controllers;


import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamory.backend.DTOs.Requests.Create.CreateUserDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateUserDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Services.Contracts.UserInterface;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Data
public class UserController {

    private final UserInterface userService;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> all(Pageable pageable){
        Page<UserResponseDTO> users =  userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> show(@PathVariable UUID id) {
        UserResponseDTO userDto = userService.getUserById(id);

        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid CreateUserDTO userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserDTO userDTO
    ){

        UserResponseDTO user = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully");
    }

}