package com.nexus.serviceImpl;

import com.nexus.dto.RegistrationResponse;
import com.nexus.dto.UserRegistrationRequest;
import com.nexus.entity.User;
import com.nexus.exception.EmailAlreadyExistsException;
import com.nexus.repository.UserRepository;
import com.nexus.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setBio(request.getBio());

        User savedUser = userRepository.save(user);

        return new RegistrationResponse(
                savedUser.getId(),
                savedUser.getName(),
                "User registered successfully!"
        );
    }
}