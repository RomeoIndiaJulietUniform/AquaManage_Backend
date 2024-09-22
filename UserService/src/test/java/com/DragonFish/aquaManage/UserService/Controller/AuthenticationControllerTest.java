package com.DragonFish.aquaManage.UserService.Controller;

import com.DragonFish.aquaManage.UserService.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testLoginSuccess(){
        AuthenticationController.LoginRequest loginRequest = new AuthenticationController.LoginRequest();
        loginRequest.setEmail("mayufindpeace@email.com");
        loginRequest.setPassword("climbandmaintainfl550");

        String mockToken = "mockJwtToken";

        when(userService.authenticateUser(loginRequest.getEmail(),loginRequest.getPassword()))
                .thenReturn(mockToken);


        ResponseEntity<?> response = authenticationController.login(loginRequest);


        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockToken, response.getBody());


        verify(userService, times(1)).authenticateUser(loginRequest.getEmail(),loginRequest.getPassword());
    }

    @Test
    void testLoginFailure(){
        AuthenticationController.LoginRequest loginRequest = new AuthenticationController.LoginRequest();
        loginRequest.setEmail("mayufindpeace@email.com");
        loginRequest.setPassword("climbandmaintainfl550");


        when(userService.authenticateUser(loginRequest.getEmail(),loginRequest.getPassword()))
                .thenThrow(new RuntimeException());


        ResponseEntity<?> response = authenticationController.login(loginRequest);


        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("Invalid credentials", response.getBody());


        verify(userService, times(1)).authenticateUser(loginRequest.getEmail(),loginRequest.getPassword());
    }

}
