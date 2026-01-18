package br.com.fiap.challenge.restaurant.core.usecase.user;

import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;

class DeleteUserImplTest {

    private UserGateway userGateway;
    private DeleteUserImpl deleteUser;

    @BeforeEach
    void setUp() {
        userGateway = Mockito.mock(UserGateway.class);
        deleteUser = new DeleteUserImpl(userGateway);
    }

    @Test
    void shouldDeleteUserById() {
        UUID userId = UUID.randomUUID();

        deleteUser.execute(userId);

        verify(userGateway).deleteUser(userId);
        verifyNoMoreInteractions(userGateway);
    }
}
