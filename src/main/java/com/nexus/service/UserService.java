package com.nexus.service;

import com.nexus.dto.request.LoginRequest;
import com.nexus.dto.response.LoginResponse;
import com.nexus.dto.response.RegistrationResponse;
import com.nexus.dto.request.UserRegistrationRequest;
import com.nexus.dto.response.UserResponse;

import java.util.List;

public interface UserService {
   // void registerUser(UserRegistrationRequest request);
   RegistrationResponse registerUser(UserRegistrationRequest request);
    LoginResponse loginUser(LoginRequest request);
    List<UserResponse> searchUsers(String keyword);
}

