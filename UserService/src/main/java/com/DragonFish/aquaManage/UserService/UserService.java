package com.DragonFish.aquaManage.UserService;

import com.DragonFish.aquaManage.UserService.User;
import com.DragonFish.aquaManage.UserService.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserServiceRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}