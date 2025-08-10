package org.teamory.backend.Services.Jwt;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.teamory.backend.Entities.User;
import org.teamory.backend.Repositories.UserRepository;

import java.util.Optional;

@Component
public class KeycloakUserProvisioningService {

    private final UserRepository userRepository;

    public KeycloakUserProvisioningService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void provisionUser(Jwt jwt) {
        String keycloakId = jwt.getSubject(); // the 'sub' claim
        Optional<User> existingUser = userRepository.findByKeycloakId(keycloakId);

        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setKeycloakId(keycloakId);
            newUser.setUsername(jwt.getClaimAsString("preferred_username"));
            newUser.setEmail(jwt.getClaimAsString("email"));
            newUser.setFullName(jwt.getClaimAsString("name"));
            // Set other fields as needed

            userRepository.save(newUser);
            System.out.println("Created new local user for keycloakId: " + keycloakId);
        }
    }
}
