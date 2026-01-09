package br.com.fiap.challenge.restaurant.core.usecase.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;
import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;

class ListUserByIdImplTest {

    private UserGateway userGateway;
    private ListUserByIdImpl listUserById;

    @BeforeEach
    void setUp() {
        userGateway = Mockito.mock(UserGateway.class);
        listUserById = new ListUserByIdImpl(userGateway);
    }

    @Test
    void shouldReturnUserWhenFound() {
        UUID userId = UUID.randomUUID();

        UserDto user = new UserDto(
                userId,
                "Jose",
                "jose@email.com",
                "jose",
                true,
                Role.CUSTOMER
        );

        when(userGateway.getUserById(userId)).thenReturn(user);

        UserDto result = listUserById.execute(userId);

        assertNotNull(result);
        assertEquals(userId, result.id());
        verify(userGateway).getUserById(userId);
    }

    @Test
    void shouldReturnNullWhenUserNotFound() {
        UUID userId = UUID.randomUUID();

        when(userGateway.getUserById(userId)).thenReturn(null);

        UserDto result = listUserById.execute(userId);

        assertNull(result);
        verify(userGateway).getUserById(userId);
    }
}
