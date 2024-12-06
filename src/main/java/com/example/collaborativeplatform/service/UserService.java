package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.user.User;
import com.example.collaborativeplatform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor-based injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Register a new user.
     *
     * @param user The user object to be registered.
     * @return The saved User object.
     * @throws IllegalArgumentException if the email is already in use.
     */
    public User registerUser(User user) {
        // Check if the email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        // Save the new user
        return userRepository.save(user);
    }

    /**
     * Find a user by their ID.
     *
     * @param id The user's ID.
     * @return An Optional containing the user if found, or empty otherwise.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Find a user by their email.
     *
     * @param email The user's email.
     * @return An Optional containing the user if found, or empty otherwise.
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Find a user by their username.
     *
     * @param username The user's username.
     * @return An Optional containing the user if found, or empty otherwise.
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
