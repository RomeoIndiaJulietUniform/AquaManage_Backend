package com.DragonFish.aquaManage.UserService.Controller;

import com.DragonFish.aquaManage.UserService.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController; // Controller under test

    @Mock
    UserService userService; // Mocked UserService dependency

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test
    void testLoginSuccess(){
        // Arrange: Create a LoginRequest and set email and password
        AuthenticationController.LoginRequest loginRequest = new AuthenticationController.LoginRequest();
        loginRequest.setEmail("mayufindpeace@email.com");
        loginRequest.setPassword("climbandmaintainfl550");

        String mockToken = "mockJwtToken"; // Mocked token returned by the UserService

        // Mocking the behavior of UserService
        when(userService.authenticateUser(loginRequest.getEmail(),loginRequest.getPassword()))
                .thenReturn(mockToken);

        // Act: Call the login method
        ResponseEntity<?> response = authenticationController.login(loginRequest);

        // Assert: Verify the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockToken, response.getBody());

        // Verify that authenticateUser was called exactly once
        verify(userService, times(1)).authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @Test
    void testLoginFailure(){
        // Arrange: Create a LoginRequest with email and password
        AuthenticationController.LoginRequest loginRequest = new AuthenticationController.LoginRequest();
        loginRequest.setEmail("mayufindpeace@email.com");
        loginRequest.setPassword("climbandmaintainfl550");

        // Mocking the behavior of UserService to throw an exception
        when(userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword()))
                .thenThrow(new RuntimeException());

        // Act: Call the login method
        ResponseEntity<?> response = authenticationController.login(loginRequest);

        // Assert: Verify the response status and body
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid credentials", response.getBody());

        // Verify that authenticateUser was called exactly once
        verify(userService, times(1)).authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
