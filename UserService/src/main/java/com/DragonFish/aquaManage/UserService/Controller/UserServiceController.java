package com.DragonFish.aquaManage.UserService.Controller;

import com.DragonFish.aquaManage.UserService.Entity.UserServiceEntity;
import com.DragonFish.aquaManage.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserServiceController {

    @Autowired
    private UserService userService;

    /**
     * Fetch all users.
     *
     * @return List of UserServiceEntity
     */
    @GetMapping
    public List<UserServiceEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * A sample endpoint for testing or demonstration purposes.
     *
     * @return String message
     */
    @GetMapping("/riju")
    public String heyBro() {
        return "Bye Bye pls american pie";
    }

    /**
     * Fetch a user by email.
     *
     * @param email User email address
     * @return ResponseEntity containing UserServiceEntity or 404 if not found
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserServiceEntity> getUserByEmail(@PathVariable("email") String email) {
        Optional<UserServiceEntity> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new user.
     *
     * @param user UserServiceEntity object containing new user data
     * @return ResponseEntity with created user and status 201
     */
    @PostMapping
    public ResponseEntity<UserServiceEntity> createUser(@RequestBody UserServiceEntity user) {
        UserServiceEntity createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Delete a user by ID.
     *
     * @param id User ID
     * @return ResponseEntity with status 204 if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a user by email.
     *
     * @param email User email address
     * @return ResponseEntity with status 204 if successful
     */
    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable("email") String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
