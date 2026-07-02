package com.nexus.service;

import com.nexus.dto.LoginRequest;
import com.nexus.dto.LoginResponse;
import com.nexus.dto.RegistrationResponse;
import com.nexus.dto.UserRegistrationRequest;

public interface UserService {
   // void registerUser(UserRegistrationRequest request);
   RegistrationResponse registerUser(UserRegistrationRequest request);
    LoginResponse loginUser(LoginRequest request);
}

