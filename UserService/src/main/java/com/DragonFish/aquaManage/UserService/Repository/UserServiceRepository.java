package com.DragonFish.aquaManage.UserService.Repository;
import com.DragonFish.aquaManage.UserService.Entity.UserServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceRepository extends JpaRepository<UserServiceEntity, Long> {

}
