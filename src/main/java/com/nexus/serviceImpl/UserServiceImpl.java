package com.nexus.serviceImpl;

import com.nexus.dto.request.LoginRequest;
import com.nexus.dto.request.UserRegistrationRequest;
import com.nexus.dto.response.LoginResponse;
import com.nexus.dto.response.RegistrationResponse;
import com.nexus.dto.response.UserResponse;
import com.nexus.entity.User;
import com.nexus.exception.EmailAlreadyExistsException;
import com.nexus.exception.InvalidPasswordException;
import com.nexus.exception.UserNotFoundException;
import com.nexus.mapper.mapper;
import com.nexus.mapper.mapper;
import com.nexus.repository.UserRepository;
import com.nexus.security.JwtService;
import com.nexus.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public RegistrationResponse registerUser(UserRegistrationRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBio(request.getBio());

        User savedUser = userRepository.save(user);

        return new RegistrationResponse(
                savedUser.getId(),
                savedUser.getName(),
                "User registered successfully!"
        );
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                "Login successful",
                token
        );
    }

    @Override
    public List<UserResponse> searchUsers(String keyword) {

        return userRepository
                .findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(mapper::toUserResponse)
                .toList();
    }
}