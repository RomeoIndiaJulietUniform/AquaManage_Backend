package com.DragonFish.aquaManage.UserService.Controller;

import com.DragonFish.aquaManage.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication Controller responsible for handling user login.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint for user login.
     *
     * @param loginRequest LoginRequest containing user email and password.
     * @return JWT token if credentials are valid, otherwise error message.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(token);  // Return JWT token if authentication is successful
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    /**
     * Inner class to handle the login request payload.
     */
    public static class LoginRequest {
        private String email;
        private String password;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
