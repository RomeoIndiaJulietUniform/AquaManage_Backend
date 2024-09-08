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

    @GetMapping
    public List<UserServiceEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/")
    String heyBro(){
        return "Bye Bye pls american pie";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserServiceEntity> getUserById(@PathVariable Long id) {
        Optional<UserServiceEntity> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserServiceEntity> createUser(@RequestBody UserServiceEntity user) {
        UserServiceEntity createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}