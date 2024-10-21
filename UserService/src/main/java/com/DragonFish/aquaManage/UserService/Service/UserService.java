package com.DragonFish.aquaManage.UserService.Service;

import com.DragonFish.aquaManage.UserService.Entity.UserServiceEntity;
import com.DragonFish.aquaManage.UserService.Repository.UserServiceRepository;
import com.DragonFish.aquaManage.UserService.Utility.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserService class handles operations related to user management,
 * including fetching, saving, deleting users, and authentication.
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserServiceRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Retrieves all users from the database.
     *
     * @return List of all UserServiceEntity objects in the database.
     */
    public List<UserServiceEntity> getAllUsers() {
        log.info("Fetching all users in the database");
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to fetch.
     * @return An Optional containing the UserServiceEntity if found, or empty otherwise.
     */
    public Optional<UserServiceEntity> getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        return userRepository.findById(id);
    }

    /**
     * Saves a new user or updates an existing user in the database.
     * The password is encoded before saving.
     *
     * @param user The UserServiceEntity object to be saved.
     * @return The saved UserServiceEntity object.
     */
    public UserServiceEntity saveUser(UserServiceEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saved user into the database");
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to be deleted.
     */
    public void deleteUser(Long id) {
        log.info("Attempting to delete user with id: {}", id);
        userRepository.deleteById(id);
        log.info("User with id {} deleted successfully from the database", id);
    }

    /**
     * Fetches a user by their email.
     *
     * @param email The email of the user to fetch.
     * @return An Optional containing the UserServiceEntity if found, or empty otherwise.
     */
    public Optional<UserServiceEntity> getUserByEmail(String email) {
        log.info("Fetching user by email: {}", email);
        return userRepository.findByEmail(email);
    }

    /**
     * Deletes a user by their email address in a transactional operation.
     *
     * @param email The email of the user to be deleted.
     */
    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
        log.info("User with email {} deleted successfully from the database", email);
    }

    /**
     * Authenticates a user by checking their email and password.
     * Generates a JWT token if authentication is successful.
     *
     * @param email The email of the user trying to authenticate.
     * @param password The password provided by the user.
     * @return A JWT token if authentication is successful.
     * @throws RuntimeException if authentication fails due to invalid credentials.
     */
    public String authenticateUser(String email, String password) {
        Optional<UserServiceEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                log.info("User authenticated with email: {}", email);
                return jwtUtil.generateToken(email);
            }
        }
        log.error("Invalid credentials for email: {}", email);
        throw new RuntimeException("Invalid credentials");
    }

    /**
     * Validates a JWT token to ensure it is valid for the given email.
     *
     * @param token The JWT token to validate.
     * @param email The email of the user to check against the token.
     * @return true if the token is valid, false otherwise.
     */
    public boolean validateToken(String token, String email) {
        log.info("Validating token for email: {}", email);
        return jwtUtil.validateToken(token, email);
    }

    /**
     * Loads the user details by their username (in this case, their email).
     * This is used by Spring Security for authentication purposes.
     *
     * @param email The email of the user.
     * @return UserDetails object containing user authentication data.
     * @throws UsernameNotFoundException if the user is not found by email.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserServiceEntity> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            log.error("User with email {} not found", email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        UserServiceEntity userEntity = user.get();
        log.info("User with email {} authenticated", email);
        return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
}
