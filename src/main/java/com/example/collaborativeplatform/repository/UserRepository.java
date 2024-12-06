package com.example.collaborativeplatform.repository;

import com.example.collaborativeplatform.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by email
    Optional<User> findByEmail(String email);

    // Find a user by username (optional for additional functionality)
    Optional<User> findByUsername(String username);
}
