package br.com.fiap.challenge.restaurant.core.usecase.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;
import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.dto.UserInput;
import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;

class UpdateUserImplTest {

    private UserGateway userGateway;
    private UpdateUserImpl updateUser;

    @BeforeEach
    void setUp() {
        userGateway = Mockito.mock(UserGateway.class);
        updateUser = new UpdateUserImpl(userGateway);
    }

    @Test
    void shouldUpdateUser() {
        UUID userId = UUID.randomUUID();

        UserInput input = new UserInput(
                userId,
                "Marcio",
                "marcio@email.com",
                "marcio_login",
                "senha123",
                true,
                Role.OWNER_RESTAURANT
        );

        UserDto updated = new UserDto(
                userId,
                "Marcio",
                "marcio@email.com",
                "marcio_login",
                true,
                Role.OWNER_RESTAURANT
        );

        when(userGateway.updateUser(input)).thenReturn(updated);

        UserDto result = updateUser.execute(input);

        assertNotNull(result);
        assertEquals("Marcio", result.name());
        assertEquals("marcio@email.com", result.email());
        assertEquals("marcio_login", result.login());
        assertTrue(result.active());
        assertEquals(Role.OWNER_RESTAURANT, result.role());

        verify(userGateway).updateUser(input);
        verifyNoMoreInteractions(userGateway);
    }
}
