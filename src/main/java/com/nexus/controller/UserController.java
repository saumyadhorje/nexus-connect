package com.nexus.controller;

import com.nexus.dto.request.LoginRequest;
import com.nexus.dto.request.UserRegistrationRequest;
import com.nexus.dto.response.LoginResponse;
import com.nexus.dto.response.RegistrationResponse;
import com.nexus.dto.response.UserResponse;
import com.nexus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        RegistrationResponse response = userService.registerUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = userService.loginUser(request);

        return ResponseEntity.ok(response);
    }

    // Search Users
    @GetMapping("/search")
    public List<UserResponse> searchUsers(
            @RequestParam String keyword) {

        return userService.searchUsers(keyword);
    }
}