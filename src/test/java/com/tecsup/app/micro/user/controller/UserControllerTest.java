package com.tecsup.app.micro.user.controller;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.entity.Role;
import com.tecsup.app.micro.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers() throws Exception {

        User u1 = new User(1L, "Juan Pérez", "juan.perez@example.com","12345",Role.ADMIN);
        User u2 = new User(2L, "Ana Torres", "ana@example.com","12345",Role.USER);

        when(userService.getAllUsers())
                .thenReturn(List.of(u1, u2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    void getUserById() throws Exception {

        User user = new User(1L, "Juan Pérez", "juan.perez@example.com","12345", Role.USER);

        when(userService.getUserById(1L))
                .thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Juan Pérez")))
                .andExpect(jsonPath("$.email", is("juan.perez@example.com")));
    }
}
