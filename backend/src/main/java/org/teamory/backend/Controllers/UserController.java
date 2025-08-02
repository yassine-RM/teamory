package org.teamory.backend.Controllers;


import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamory.backend.DTOs.Requests.Create.CreateUserDTO;
import org.teamory.backend.DTOs.Responses.UserResponseDTO;
import org.teamory.backend.Services.Contracts.UserInterface;

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
    public ResponseEntity<UserResponseDTO> show(@PathVariable String id) {
        UserResponseDTO userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody CreateUserDTO userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

}

