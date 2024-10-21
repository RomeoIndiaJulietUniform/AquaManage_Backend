package com.DragonFish.aquaManage.UserService.Controller;

import com.DragonFish.aquaManage.UserService.Entity.UserServiceEntity;
import com.DragonFish.aquaManage.UserService.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceControllerTest {

    @InjectMocks
    private UserServiceController userServiceController; // Controller under test

    @Mock
    private UserService userService; // Mocked UserService dependency

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test
    void testGetAllUsers() {
        List<UserServiceEntity> users = new ArrayList<>();
        users.add(new UserServiceEntity()); // Create a sample user entity

        when(userService.getAllUsers()).thenReturn(users); // Mock the service call

        List<UserServiceEntity> result = userServiceController.getAllUsers(); // Call the method to test

        assertEquals(1, result.size()); // Verify the size of the returned list
        verify(userService, times(1)).getAllUsers(); // Verify that the service method was called once
    }

    @Test
    void testGetUserByEmail_UserExists() {
        String email = "test@example.com";
        UserServiceEntity user = new UserServiceEntity(); // Create a sample user entity
        when(userService.getUserByEmail(email)).thenReturn(Optional.of(user)); // Mock the service call

        ResponseEntity<UserServiceEntity> response = userServiceController.getUserByEmail(email); // Call the method to test

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Verify the response status
        assertEquals(user, response.getBody()); // Verify the response body
        verify(userService, times(1)).getUserByEmail(email); // Verify that the service method was called once
    }

    @Test
    void testGetUserByEmail_UserNotFound() {
        String email = "notfound@example.com";
        when(userService.getUserByEmail(email)).thenReturn(Optional.empty()); // Mock the service call

        ResponseEntity<UserServiceEntity> response = userServiceController.getUserByEmail(email); // Call the method to test

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Verify the response status
        verify(userService, times(1)).getUserByEmail(email); // Verify that the service method was called once
    }

    @Test
    void testCreateUser() {
        UserServiceEntity user = new UserServiceEntity(); // Create a sample user entity
        when(userService.saveUser(any(UserServiceEntity.class))).thenReturn(user); // Mock the service call

        ResponseEntity<UserServiceEntity> response = userServiceController.createUser(user); // Call the method to test

        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Verify the response status
        assertEquals(user, response.getBody()); // Verify the response body
        verify(userService, times(1)).saveUser(user); // Verify that the service method was called once
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L; // Sample user ID

        ResponseEntity<Void> response = userServiceController.deleteUser(userId); // Call the method to test

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Verify the response status
        verify(userService, times(1)).deleteUser(userId); // Verify that the service method was called once
    }

    @Test
    void testDeleteUserByEmail() {
        String email = "test@example.com"; // Sample email

        ResponseEntity<Void> response = userServiceController.deleteUserByEmail(email); // Call the method to test

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Verify the response status
        verify(userService, times(1)).deleteUserByEmail(email); // Verify that the service method was called once
    }
}
