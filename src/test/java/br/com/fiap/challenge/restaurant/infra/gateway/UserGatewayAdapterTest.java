package br.com.fiap.challenge.restaurant.infra.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;
import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.dto.UserInput;
import br.com.fiap.challenge.restaurant.infra.entity.User;
import br.com.fiap.challenge.restaurant.infra.repository.UserRepository;

class UserGatewayAdapterTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserGatewayAdapter userGatewayAdapter;

    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
    }

    @Test
    void shouldReturnAllUsers() {
        User user = new User("Mariana", "mariana@email.com", "mariana", "123456", true, Role.CUSTOMER);
        user.setId(userId);

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserDto> result = userGatewayAdapter.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("Mariana", result.get(0).name());
        assertEquals("mariana@email.com", result.get(0).email());
        assertEquals("mariana", result.get(0).login());
        assertTrue(result.get(0).active());
        assertEquals(Role.CUSTOMER, result.get(0).role());

        verify(userRepository).findAll();
    }

    @Test
    void shouldReturnUserById() {
        User user = new User("Mariana", "mariana@email.com", "mariana", "123456", true, Role.OWNER_RESTAURANT);
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDto result = userGatewayAdapter.getUserById(userId);

        assertNotNull(result);
        assertEquals(userId, result.id());
        assertEquals("mariana", result.login());
        assertEquals(Role.OWNER_RESTAURANT, result.role());

        verify(userRepository).findById(userId);
    }

    @Test
    void shouldCreateUser() {
        UserInput input = new UserInput(
                null,
                "Mariana",
                "mariana@email.com",
                "mariana",
                "123456",
                true,
                Role.OWNER_RESTAURANT
        );

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDto result = userGatewayAdapter.createUser(input);

        assertEquals("Mariana", result.name());
        assertEquals("mariana@email.com", result.email());
        assertEquals("mariana", result.login());
        assertTrue(result.active());
        assertEquals(Role.OWNER_RESTAURANT, result.role());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldUpdateUser() {
        User user = new User("Jose", "jose@email.com", "jose", "oldpass", false, Role.CUSTOMER);
        user.setId(userId);

        UserInput input = new UserInput(
                userId,
                "Jose Maria",
                "josemaria@email.com",
                "josemaria",
                "newpass",
                true,
                Role.ADMIN
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        UserDto result = userGatewayAdapter.updateUser(input);

        assertEquals("Jose Maria", result.name());
        assertEquals("josemaria@email.com", result.email());
        assertEquals("josemaria", result.login());
        assertTrue(result.active());
        assertEquals(Role.ADMIN, result.role());

        verify(userRepository).findById(userId);
        verify(userRepository).save(user);
    }

    @Test
    void shouldDeleteUser() {
        userGatewayAdapter.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    void shouldReturnTrueWhenEmailExists() {
        when(userRepository.existsByEmailIgnoreCase("joao@email.com")).thenReturn(true);

        boolean exists = userGatewayAdapter.existsByEmail("joao@email.com");

        assertTrue(exists);
        verify(userRepository).existsByEmailIgnoreCase("joao@email.com");
    }

    @Test
    void shouldReturnTrueWhenLoginExists() {
        when(userRepository.existsByLoginIgnoreCase("joao")).thenReturn(true);

        boolean exists = userGatewayAdapter.existsByLogin("joao");

        assertTrue(exists);
        verify(userRepository).existsByLoginIgnoreCase("joao");
    }
}
