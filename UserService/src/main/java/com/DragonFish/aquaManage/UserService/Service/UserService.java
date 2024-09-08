package com.DragonFish.aquaManage.UserService.Service;

import com.DragonFish.aquaManage.UserService.Repository.UserServiceRepository;
import com.DragonFish.aquaManage.UserService.Entity.UserServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserServiceRepository userRepository;

    public List<UserServiceEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserServiceEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserServiceEntity saveUser(UserServiceEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
