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

    @Mock
    private UserServiceRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById_Success() {
        Long userId = 1L;
        UserServiceEntity user = new UserServiceEntity();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<UserServiceEntity> result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserById_NotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<UserServiceEntity> result = userService.getUserById(userId);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testSaveUser() {
        UserServiceEntity user = new UserServiceEntity();
        user.setPassword("plain_password");
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(user)).thenReturn(user);

        UserServiceEntity savedUser = userService.saveUser(user);

        assertEquals("encoded_password", savedUser.getPassword());
        verify(passwordEncoder, times(1)).encode("plain_password");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testAuthenticateUser_Success() {
        String email = "test@example.com";
        String password = "password";
        UserServiceEntity user = new UserServiceEntity();
        user.setEmail(email);
        user.setPassword("encoded_password");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, "encoded_password")).thenReturn(true);
        when(jwtUtil.generateToken(email)).thenReturn("token");

        String token = userService.authenticateUser(email, password);

        assertEquals("token", token);
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).matches(password, "encoded_password");
        verify(jwtUtil, times(1)).generateToken(email);
    }

    @Test
    void testAuthenticateUser_InvalidCredentials() {
        String email = "test@example.com";
        String password = "password";
        UserServiceEntity user = new UserServiceEntity();
        user.setEmail(email);
        user.setPassword("encoded_password");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, "encoded_password")).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.authenticateUser(email, password);
        });

        assertEquals("Invalid credentials", exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).matches(password, "encoded_password");
    }

    @Test
    void testValidateToken_Success() {
        String token = "token";
        String email = "test@example.com";

        when(jwtUtil.validateToken(token, email)).thenReturn(true);

        boolean isValid = userService.validateToken(token, email);

        assertTrue(isValid);
        verify(jwtUtil, times(1)).validateToken(token, email);
    }

    @Test
    void testLoadUserByUsername_Success() {
        String email = "test@example.com";
        UserServiceEntity user = new UserServiceEntity();
        user.setEmail(email);
        user.setPassword("encoded_password");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(email);

        assertEquals(email, userDetails.getUsername());
        assertEquals("encoded_password", userDetails.getPassword());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(email);
        });

        assertEquals("User not found with email: test@example.com", exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
    }
}
