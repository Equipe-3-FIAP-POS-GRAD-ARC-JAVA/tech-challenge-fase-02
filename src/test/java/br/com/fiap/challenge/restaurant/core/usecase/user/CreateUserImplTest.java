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

class CreateUserImplTest {

    private UserGateway userGateway;
    private CreateUserImpl createUser;

    @BeforeEach
    void setUp() {
        userGateway = Mockito.mock(UserGateway.class);
        createUser = new CreateUserImpl(userGateway);
    }

    @Test
    void shouldCreateUserWhenEmailAndLoginDoNotExist() {
        UUID id = UUID.randomUUID();

        UserInput input = new UserInput(
                null,
                "Marcio",
                "marcio@email.com",
                "marcio",
                "123",
                true,
                Role.OWNER_RESTAURANT
        );

        UserDto expected = new UserDto(
                id,
                "Marcio",
                "marcio@email.com",
                "marcio",
                true,
                Role.OWNER_RESTAURANT
        );

        when(userGateway.existsByEmail(input.email())).thenReturn(false);
        when(userGateway.existsByLogin(input.login())).thenReturn(false);
        when(userGateway.createUser(input)).thenReturn(expected);

        UserDto result = createUser.execute(input);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("Marcio", result.name());
        assertEquals("marcio@email.com", result.email());
        assertEquals("marcio", result.login());
        assertTrue(result.active());
        assertEquals(Role.OWNER_RESTAURANT, result.role());

        verify(userGateway).existsByEmail("marcio@email.com");
        verify(userGateway).existsByLogin("marcio");
        verify(userGateway).createUser(input);
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        UserInput input = new UserInput(
                null,
                "Marcio",
                "marcio@email.com",
                "marcio",
                "123",
                true,
                Role.CUSTOMER
        );

        when(userGateway.existsByEmail(input.email())).thenReturn(true);

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> createUser.execute(input));
        assertNotNull(ex);

        verify(userGateway).existsByEmail("marcio@email.com");
        verify(userGateway, never()).existsByLogin(anyString());
        verify(userGateway, never()).createUser(any(UserInput.class));
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void shouldThrowExceptionWhenLoginAlreadyExists() {
        UserInput input = new UserInput(
                null,
                "Marcio",
                "marcio@email.com",
                "marcio",
                "123",
                true,
                Role.CUSTOMER
        );

        when(userGateway.existsByEmail(input.email())).thenReturn(false);
        when(userGateway.existsByLogin(input.login())).thenReturn(true);

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> createUser.execute(input));
        assertNotNull(ex);

        verify(userGateway).existsByEmail("marcio@email.com");
        verify(userGateway).existsByLogin("marcio");
        verify(userGateway, never()).createUser(any(UserInput.class));
        verifyNoMoreInteractions(userGateway);
    }
}
