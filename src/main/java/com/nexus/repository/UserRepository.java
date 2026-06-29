package com.nexus.repository;

import com.nexus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //so optional is basically just saying ki agar woh email ka koi banda present na ho , toh crash mat karna
    ///encourages the caller to handle that case safely instead of risking a NullPointerException.
    Optional<User> findByEmail(String email);
}
