package com.DragonFish.aquaManage.UserService;

import org.springframework.data.jpa.repository.JpaRepository;
import com.DragonFish.aquaManage.UserService.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceRepository extends JpaRepository<User, Long> {
}