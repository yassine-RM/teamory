package org.teamory.backend.Controllers;


import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teamory.backend.Entities.User;
import org.teamory.backend.Services.Interfaces.UserInterface;

@RestController
@RequestMapping("/api/users")
@Data
public class UserController {

    private final UserInterface userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}

