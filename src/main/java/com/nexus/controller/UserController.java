package com.nexus.controller;

import com.nexus.dto.LoginRequest;
import com.nexus.dto.LoginResponse;
import com.nexus.dto.RegistrationResponse;
import com.nexus.dto.UserRegistrationRequest;
import com.nexus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        RegistrationResponse response = userService.registerUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = userService.loginUser(request);

        return ResponseEntity.ok(response);
    }
}