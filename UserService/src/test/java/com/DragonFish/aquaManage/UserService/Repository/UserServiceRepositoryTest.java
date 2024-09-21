package com.DragonFish.aquaManage.UserService.Repository;

import com.DragonFish.aquaManage.UserService.Entity.UserServiceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceRepositoryTest {

    @Mock
    private UserServiceRepository userServiceRepository;

    private UserServiceEntity user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new UserServiceEntity();
        user.setId(1L);
        user.setEmail("test@example.com");
    }

    @Test
    void testFindByEmail() {
        when(userServiceRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<UserServiceEntity> foundUser = userServiceRepository.findByEmail("test@example.com");

        assertEquals(user.getEmail(), foundUser.get().getEmail());
        verify(userServiceRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testDeleteByEmail() {
        doNothing().when(userServiceRepository).deleteByEmail(anyString());

        userServiceRepository.deleteByEmail("test@example.com");

        verify(userServiceRepository, times(1)).deleteByEmail("test@example.com");
    }
}
