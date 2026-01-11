package br.com.fiap.challenge.restaurant.infra.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;
import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.dto.UserInput;
import br.com.fiap.challenge.restaurant.core.usecase.user.CreateUser;
import br.com.fiap.challenge.restaurant.core.usecase.user.DeleteUser;
import br.com.fiap.challenge.restaurant.core.usecase.user.ListAllUser;
import br.com.fiap.challenge.restaurant.core.usecase.user.ListUserById;
import br.com.fiap.challenge.restaurant.core.usecase.user.UpdateUser;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateUser createUser;

    @MockitoBean
    private ListAllUser listAllUser;

    @MockitoBean
    private ListUserById listUserById;

    @MockitoBean
    private UpdateUser updateUser;

    @MockitoBean
    private DeleteUser deleteUser;

    @Test
    void shouldCreateUser() throws Exception {
        UUID createdId = UUID.randomUUID();

        UserInput input = new UserInput(
                null,
                "Mariana",
                "mariana@email.com",
                "mariana_login",
                "senha123",
                true,
                Role.OWNER_RESTAURANT
        );

        UserDto output = new UserDto(
                createdId,
                "Mariana",
                "mariana@email.com",
                "mariana_login",
                true,
                Role.OWNER_RESTAURANT
        );

        when(createUser.execute(any(UserInput.class))).thenReturn(output);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(createdId.toString()))
                .andExpect(jsonPath("$.name").value("Mariana"))
                .andExpect(jsonPath("$.email").value("mariana@email.com"))
                .andExpect(jsonPath("$.login").value("mariana_login"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.role").value("OWNER_RESTAURANT"))
                .andExpect(jsonPath("$.password").doesNotExist());

        verify(createUser).execute(any(UserInput.class));
    }

    @Test
    void shouldListAllUsers() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        List<UserDto> users = List.of(
                new UserDto(id1, "Mariana", "mariana@email.com", "mariana_login", true, Role.OWNER_RESTAURANT),
                new UserDto(id2, "Maria", "maria@email.com", "maria_login", false, Role.CUSTOMER)
        );

        when(listAllUser.execute()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(id1.toString()))
                .andExpect(jsonPath("$[0].name").value("Mariana"))
                .andExpect(jsonPath("$[0].login").value("mariana_login"))
                .andExpect(jsonPath("$[0].role").value("OWNER_RESTAURANT"))
                .andExpect(jsonPath("$[1].id").value(id2.toString()))
                .andExpect(jsonPath("$[1].name").value("Maria"))
                .andExpect(jsonPath("$[1].login").value("maria_login"))
                .andExpect(jsonPath("$[1].role").value("CUSTOMER"));

        verify(listAllUser).execute();
    }

    @Test
    void shouldGetUserByIdWhenFound() throws Exception {
        UUID userId = UUID.randomUUID();

        UserDto output = new UserDto(
                userId,
                "Mariana",
                "mariana@email.com",
                "mariana_login",
                true,
                Role.ADMIN
        );

        when(listUserById.execute(userId)).thenReturn(output);

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.name").value("Mariana"))
                .andExpect(jsonPath("$.email").value("mariana@email.com"))
                .andExpect(jsonPath("$.login").value("mariana_login"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.role").value("ADMIN"))
                .andExpect(jsonPath("$.password").doesNotExist());

        verify(listUserById).execute(userId);
    }

    @Test
    void shouldReturn404WhenUserNotFound() throws Exception {
        UUID userId = UUID.randomUUID();

        when(listUserById.execute(userId)).thenReturn(null);

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isNotFound());

        verify(listUserById).execute(userId);
    }

    @Test
    void shouldUpdateUser() throws Exception {
        UUID pathId = UUID.randomUUID();

        // input sem id (id vem do path)
        UserInput input = new UserInput(
                null,
                "Jose Maria",
                "josemaria@email.com",
                "josemaria_login",
                "novaSenha123",
                true,
                Role.OWNER_RESTAURANT
        );

        UserDto output = new UserDto(
                pathId,
                "Jose Maria",
                "josemaria@email.com",
                "josemaria_login",
                true,
                Role.OWNER_RESTAURANT
        );

        when(updateUser.execute(any(UserInput.class))).thenReturn(output);

        mockMvc.perform(put("/api/users/{id}", pathId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(pathId.toString()))
                .andExpect(jsonPath("$.name").value("Jose Maria"))
                .andExpect(jsonPath("$.email").value("josemaria@email.com"))
                .andExpect(jsonPath("$.login").value("josemaria_login"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.role").value("OWNER_RESTAURANT"))
                .andExpect(jsonPath("$.password").doesNotExist());

        // garante que o controller montou o UserInput com id do PATH
        ArgumentCaptor<UserInput> captor = ArgumentCaptor.forClass(UserInput.class);
        verify(updateUser).execute(captor.capture());

        UserInput sentToUseCase = captor.getValue();
        org.junit.jupiter.api.Assertions.assertEquals(pathId, sentToUseCase.id());
        org.junit.jupiter.api.Assertions.assertEquals("Jose Maria", sentToUseCase.name());
        org.junit.jupiter.api.Assertions.assertEquals("josemaria@email.com", sentToUseCase.email());
        org.junit.jupiter.api.Assertions.assertEquals("josemaria_login", sentToUseCase.login());
        org.junit.jupiter.api.Assertions.assertEquals("novaSenha123", sentToUseCase.password());
        org.junit.jupiter.api.Assertions.assertTrue(sentToUseCase.active());
        org.junit.jupiter.api.Assertions.assertEquals(Role.OWNER_RESTAURANT, sentToUseCase.role());
    }

    @Test
    void shouldDeleteUser() throws Exception {
        UUID userId = UUID.randomUUID();

        mockMvc.perform(delete("/api/users/{id}", userId))
                .andExpect(status().isNoContent());

        verify(deleteUser).execute(userId);
    }
}
