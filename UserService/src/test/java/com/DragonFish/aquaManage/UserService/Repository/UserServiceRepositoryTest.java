//package com.DragonFish.aquaManage.UserService.Repository;
//
//import com.DragonFish.aquaManage.UserService.Entity.UserServiceEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@SpringJUnitConfig
//public class UserServiceRepositoryTest {
//
//    @Autowired
//    private UserServiceRepository repository;
//
//    @Test
//    public void testSaveUser() {
//        UserServiceEntity user = new UserServiceEntity();
//        user.setName("Test User");
//        user.setUserName("testuser");
//        user.setEmail("testuser@example.com");
//        user.setPassword("password");
//
//        UserServiceEntity savedUser = repository.save(user);
//
//        assertThat(savedUser).isNotNull();
//        assertThat(savedUser.getId()).isNotNull();
//        assertThat(savedUser.getName()).isEqualTo("Test User");
//    }
//}
