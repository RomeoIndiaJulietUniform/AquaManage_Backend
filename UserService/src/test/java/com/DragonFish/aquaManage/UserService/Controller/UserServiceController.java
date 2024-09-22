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
    private UserServiceController userServiceController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<UserServiceEntity> users = new ArrayList<>();
        users.add(new UserServiceEntity());

        when(userService.getAllUsers()).thenReturn(users);

        List<UserServiceEntity> result = userServiceController.getAllUsers();

        assertEquals(1, result.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserByEmail_UserExists() {
        String email = "test@example.com";
        UserServiceEntity user = new UserServiceEntity();
        when(userService.getUserByEmail(email)).thenReturn(Optional.of(user));

        ResponseEntity<UserServiceEntity> response = userServiceController.getUserByEmail(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserByEmail(email);
    }

    @Test
    void testGetUserByEmail_UserNotFound() {
        String email = "notfound@example.com";
        when(userService.getUserByEmail(email)).thenReturn(Optional.empty());

        ResponseEntity<UserServiceEntity> response = userServiceController.getUserByEmail(email);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserByEmail(email);
    }

    @Test
    void testCreateUser() {
        UserServiceEntity user = new UserServiceEntity();
        when(userService.saveUser(any(UserServiceEntity.class))).thenReturn(user);

        ResponseEntity<UserServiceEntity> response = userServiceController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        ResponseEntity<Void> response = userServiceController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void testDeleteUserByEmail() {
        String email = "test@example.com";

        ResponseEntity<Void> response = userServiceController.deleteUserByEmail(email);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUserByEmail(email);
    }
}
