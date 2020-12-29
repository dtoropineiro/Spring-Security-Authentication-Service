package com.dtoropineiro.authservice.service;

import com.dtoropineiro.authservice.model.User;
import com.dtoropineiro.authservice.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    ResponseEntity<?> registerUser(SignupRequest signUpRequest);
    Long getUserId(String username);
    Optional<User> getUser(String username);
}
