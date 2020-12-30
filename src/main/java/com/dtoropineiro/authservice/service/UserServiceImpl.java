package com.dtoropineiro.authservice.service;

import com.dtoropineiro.authservice.model.EnumRole;
import com.dtoropineiro.authservice.model.Role;
import com.dtoropineiro.authservice.model.User;
import com.dtoropineiro.authservice.payload.request.SignupRequest;
import com.dtoropineiro.authservice.payload.response.MessageResponse;
import com.dtoropineiro.authservice.repository.RoleRepository;
import com.dtoropineiro.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private static final String ROLE_NOT_FOUND = "Error: Role is not found.";

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {

        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(MessageResponse.builder()
                            .message("Error: Username is already taken.")
                            .build());
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(MessageResponse.builder()
                            .message("Error: Email is already in use.")
                            .build());
        }


        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(EnumRole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(MessageResponse.builder()
                .message("User registered successfully.")
                .build());
    }

    @Override
    public Long getUserId(String username) {
        Optional<User> userOptional = getUser(username);
        return userOptional.map(User::getId).orElse(null);
    }

    @Override
    public Optional<User> getUser(String username){
        return userRepository.findByUsername(username);
    }

}
