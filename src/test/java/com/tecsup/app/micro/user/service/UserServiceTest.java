package com.tecsup.app.micro.user.service;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.entity.Role;
import com.tecsup.app.micro.user.entity.UserEntity;
import com.tecsup.app.micro.user.mapper.UserMapper;
import com.tecsup.app.micro.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static java.time.LocalTime.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void getUserById() {

        Long ID = 100L;
        String NAME = "Eder";
        String EMAIL = "eder@gmail.com";

        User existingUser = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .build();

        when(userRepository.findById(ID))
                .thenReturn(Optional.of(userMapper.toEntity(existingUser)));

        User realUser = userService.getUserById(ID);

        assertNotNull(realUser);
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(EMAIL, realUser.getEmail());
    }

    @Test
    void testCreateUser() {
        Long NEW_ID = 1L;

        User inputDTO = User.builder()
                .name("Eder")
                .email("eder@gmail.com")
                .role(Role.valueOf("USER"))
                .password("")
                .build();

        UserEntity entityToSave = userMapper.toEntity(inputDTO);

        UserEntity savedEntity = new UserEntity(
        );

        when(userRepository.save(entityToSave)).thenReturn(savedEntity);

        User result = userService.createUser(inputDTO);

        assertNotNull(result);
        assertEquals(NEW_ID, result.getId());
        assertEquals("Eder", result.getName());
        assertEquals("eder@gmail.com", result.getEmail());
        assertEquals("", result.getPassword());
        assertEquals("USER", result.getRole());
    }

    @Test
    void testDeleteUser() {
        Long USER_ID = 1L;

        when(userRepository.existsById(USER_ID)).thenReturn(true);

        assertDoesNotThrow(() -> userService.deleteUser(USER_ID));

        verify(userRepository).deleteById(USER_ID);
    }

    @Test
    void testDeleteUserNotFound() {
        Long USER_ID = 1L;

        when(userRepository.existsById(USER_ID)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.deleteUser(USER_ID));

        assertEquals("User no encontrado con id: " + USER_ID,
                exception.getMessage());
    }
}
