package com.tecsup.app.micro.user.service;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.entity.Role;
import com.tecsup.app.micro.user.entity.UserEntity;
import com.tecsup.app.micro.user.mapper.UserMapper;
import com.tecsup.app.micro.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    // -----------------------------
    // getUserById
    // -----------------------------
    @Test
    void testGetUserById() {

        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("Juan Pérez");
        entity.setEmail("juan.perez@example.com");
        entity.setPassword("12345");
        entity.setRole(Role.USER);

        User expected = new User(
                1L,
                "Juan Pérez",
                "juan.perez@example.com",
                "12345",
                Role.USER
        );

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(entity));
        when(userMapper.toDomain(entity))
                .thenReturn(expected);

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan Pérez", result.getName());
        assertEquals(Role.USER, result.getRole());
    }

    // -----------------------------
    // getAllUsers
    // -----------------------------
    @Test
    void testGetAllUsers() {

        UserEntity e1 = new UserEntity();
        e1.setId(1L);
        e1.setName("Juan Pérez");
        e1.setEmail("juan.perez@example.com");
        e1.setPassword("12345");
        e1.setRole(Role.USER);

        UserEntity e2 = new UserEntity();
        e2.setId(2L);
        e2.setName("Ana Torres");
        e2.setEmail("ana@example.com");
        e2.setPassword("12345");
        e2.setRole(Role.ADMIN);

        List<UserEntity> entities = List.of(e1, e2);

        User u1 = new User(1L, "Juan Pérez", "juan.perez@example.com", "12345", Role.USER);
        User u2 = new User(2L, "Ana Torres", "ana@example.com", "12345", Role.ADMIN);

        when(userRepository.findAll())
                .thenReturn(entities);
        when(userMapper.toDomain(entities))
                .thenReturn(List.of(u1, u2));

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Juan Pérez", result.get(0).getName());
    }

    // -----------------------------
    // createUser
    // -----------------------------
    @Test
    void testCreateUser() {

        User input = new User(
                null,
                "Juan Pérez",
                "juan.perez@example.com",
                "12345",
                Role.USER
        );

        UserEntity entityToSave = new UserEntity();
        entityToSave.setName("Juan Pérez");
        entityToSave.setEmail("juan.perez@example.com");
        entityToSave.setPassword("12345");
        entityToSave.setRole(Role.USER);

        UserEntity savedEntity = new UserEntity();
        savedEntity.setId(1L);
        savedEntity.setName("Juan Pérez");
        savedEntity.setEmail("juan.perez@example.com");
        savedEntity.setPassword("12345");
        savedEntity.setRole(Role.USER);

        User expected = new User(
                1L,
                "Juan Pérez",
                "juan.perez@example.com",
                "12345",
                Role.USER
        );

        when(userMapper.toEntity(input))
                .thenReturn(entityToSave);
        when(userRepository.save(entityToSave))
                .thenReturn(savedEntity);
        when(userMapper.toDomain(savedEntity))
                .thenReturn(expected);

        User result = userService.createUser(input);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan Pérez", result.getName());
        assertEquals(Role.USER, result.getRole());
    }

    // -----------------------------
    // deleteUser
    // -----------------------------
    @Test
    void testDeleteUser() {

        when(userRepository.existsById(1L))
                .thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void testDeleteUserNotFound() {

        when(userRepository.existsById(1L))
                .thenReturn(false);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> userService.deleteUser(1L)
        );

        assertTrue(ex.getMessage().contains("User no encontrado"));
    }
}
