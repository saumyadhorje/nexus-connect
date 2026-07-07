package com.nexus.controller;

import com.nexus.dto.response.UserResponse;
import com.nexus.entity.User;
import com.nexus.mapper.mapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @GetMapping
    public UserResponse getProfile(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return mapper.toUserResponse(user);
    }
}