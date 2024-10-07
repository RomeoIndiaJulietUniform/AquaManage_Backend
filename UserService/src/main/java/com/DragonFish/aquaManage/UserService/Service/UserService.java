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

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserServiceRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserServiceEntity> getAllUsers() {
        log.info("Fetching all users in the database");
        return userRepository.findAll();
    }

    public Optional<UserServiceEntity> getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        return userRepository.findById(id);
    }

    public UserServiceEntity saveUser(UserServiceEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("saved user into the database");
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        log.info("Attempting to delete user with id: {}", id);
        userRepository.deleteById(id);
        log.info("User with id {} deleted successfully from the database", id);
    }

    public Optional<UserServiceEntity> getUserByEmail(String email) {
        log.info("Fetching users by email in the database");
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
        log.info("User with email {} deleted successfully from the database", email);
    }

    public String authenticateUser(String email, String password) {
        Optional<UserServiceEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                log.info("user authenticated with email: {}",email);
                return jwtUtil.generateToken(email);
            }
        }
        log.error("Invalid credentials for email: {} ", email);
        throw new RuntimeException("Invalid credentials");
    }

    public boolean validateToken(String token, String email) {
        log.info("validating token");
        return jwtUtil.validateToken(token, email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserServiceEntity> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            log.error("User with email {}  not found",email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        UserServiceEntity userEntity = user.get();
        log.info("user with email {} authenticated",email);
        return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
}
