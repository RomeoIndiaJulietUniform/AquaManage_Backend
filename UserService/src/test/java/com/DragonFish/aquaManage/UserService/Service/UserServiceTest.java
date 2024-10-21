package com.DragonFish.aquaManage.UserService.Service;

import com.DragonFish.aquaManage.UserService.Entity.UserServiceEntity;
import com.DragonFish.aquaManage.UserService.Repository.UserServiceRepository;
import com.DragonFish.aquaManage.UserService.Utility.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    // Mocks for dependencies of UserService
    @Mock
    private UserServiceRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    // Injecting mocks into UserService to test it independently
    @InjectMocks
    private UserService userService;

    // Method to initialize mocks before each test
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks for this test
    }

    // Test to ensure that getUserById returns the correct user when found
    @Test
    void testGetUserById_Success() {
        Long userId = 1L;
        UserServiceEntity user = new UserServiceEntity();
        user.setId(userId); // Setting up a mock user with ID 1
        when(userRepository.findById(userId)).thenReturn(Optional.of(user)); // Mocking repository behavior

        Optional<UserServiceEntity> result = userService.getUserById(userId); // Calling method under test

        assertTrue(result.isPresent()); // Verifying that the result is present
        assertEquals(userId, result.get().getId()); // Asserting that the returned ID is correct
        verify(userRepository, times(1)).findById(userId); // Verifying that the repository method was called once
    }

    // Test to ensure that getUserById returns empty when the user is not found
    @Test
    void testGetUserById_NotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty()); // Mocking repository to return empty

        Optional<UserServiceEntity> result = userService.getUserById(userId); // Calling method under test

        assertFalse(result.isPresent()); // Asserting that the result is empty
        verify(userRepository, times(1)).findById(userId); // Verifying repository call
    }

    // Test to verify that saveUser correctly encodes the password and saves the user
    @Test
    void testSaveUser() {
        UserServiceEntity user = new UserServiceEntity();
        user.setPassword("plain_password"); // Setting a plain text password
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encoded_password"); // Mocking password encoding
        when(userRepository.save(user)).thenReturn(user); // Mocking repository save behavior

        UserServiceEntity savedUser = userService.saveUser(user); // Calling method under test

        assertEquals("encoded_password", savedUser.getPassword()); // Asserting that password was encoded
        verify(passwordEncoder, times(1)).encode("plain_password"); // Verifying encoding call
        verify(userRepository, times(1)).save(user); // Verifying save call
    }

    // Test to verify that deleteUser successfully deletes a user by ID
    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId); // Mocking deletion behavior

        userService.deleteUser(userId); // Calling method under test

        verify(userRepository, times(1)).deleteById(userId); // Verifying deletion call
    }

    // Test to verify that authenticateUser returns a token when the user provides valid credentials
    @Test
    void testAuthenticateUser_Success() {
        String email = "test@example.com";
        String password = "password";
        UserServiceEntity user = new UserServiceEntity();
        user.setEmail(email);
        user.setPassword("encoded_password");

        // Mocking repository, password encoder, and JWT token generation behavior
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, "encoded_password")).thenReturn(true);
        when(jwtUtil.generateToken(email)).thenReturn("token");

        String token = userService.authenticateUser(email, password); // Calling method under test

        assertEquals("token", token); // Asserting that token is generated
        verify(userRepository, times(1)).findByEmail(email); // Verifying repository call
        verify(passwordEncoder, times(1)).matches(password, "encoded_password"); // Verifying password match
        verify(jwtUtil, times(1)).generateToken(email); // Verifying token generation
    }

    // Test to verify that authenticateUser throws an exception for invalid credentials
    @Test
    void testAuthenticateUser_InvalidCredentials() {
        String email = "test@example.com";
        String password = "password";
        UserServiceEntity user = new UserServiceEntity();
        user.setEmail(email);
        user.setPassword("encoded_password");

        // Mocking repository and password encoder to simulate invalid credentials
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, "encoded_password")).thenReturn(false);

        // Expecting a RuntimeException to be thrown
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.authenticateUser(email, password);
        });

        assertEquals("Invalid credentials", exception.getMessage()); // Asserting the exception message
        verify(userRepository, times(1)).findByEmail(email); // Verifying repository call
        verify(passwordEncoder, times(1)).matches(password, "encoded_password"); // Verifying password match
    }

    // Test to ensure that validateToken returns true for a valid token
    @Test
    void testValidateToken_Success() {
        String token = "token";
        String email = "test@example.com";

        // Mocking token validation behavior
        when(jwtUtil.validateToken(token, email)).thenReturn(true);

        boolean isValid = userService.validateToken(token, email); // Calling method under test

        assertTrue(isValid); // Asserting that token is valid
        verify(jwtUtil, times(1)).validateToken(token, email); // Verifying token validation call
    }

    // Test to ensure that loadUserByUsername returns UserDetails when user is found
    @Test
    void testLoadUserByUsername_Success() {
        String email = "test@example.com";
        UserServiceEntity user = new UserServiceEntity();
        user.setEmail(email);
        user.setPassword("encoded_password");

        // Mocking repository behavior
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(email); // Calling method under test

        assertEquals(email, userDetails.getUsername()); // Asserting the username matches
        assertEquals("encoded_password", userDetails.getPassword()); // Asserting the password matches
        verify(userRepository, times(1)).findByEmail(email); // Verifying repository call
    }

    // Test to ensure that loadUserByUsername throws UsernameNotFoundException when user is not found
    @Test
    void testLoadUserByUsername_UserNotFound() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty()); // Mocking repository to return empty

        // Expecting UsernameNotFoundException to be thrown
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(email);
        });

        assertEquals("User not found with email: test@example.com", exception.getMessage()); // Asserting exception message
        verify(userRepository, times(1)).findByEmail(email); // Verifying repository call
    }
}
