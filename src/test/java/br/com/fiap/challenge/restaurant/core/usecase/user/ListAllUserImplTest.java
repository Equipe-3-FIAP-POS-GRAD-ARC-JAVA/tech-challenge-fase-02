package br.com.fiap.challenge.restaurant.core.usecase.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;
import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;

class ListAllUserImplTest {

    private UserGateway userGateway;
    private ListAllUserImpl listAllUser;

    @BeforeEach
    void setUp() {
        userGateway = Mockito.mock(UserGateway.class);
        listAllUser = new ListAllUserImpl(userGateway);
    }

    @Test
    void shouldReturnAllUsers() {
        List<UserDto> users = List.of(
                new UserDto(UUID.randomUUID(), "Jose", "jose@email.com", "marcio", true, Role.CUSTOMER),
                new UserDto(UUID.randomUUID(), "Maria", "maria@email.com", "maria", false, Role.OWNER_RESTAURANT)
        );

        when(userGateway.getAllUsers()).thenReturn(users);

        List<UserDto> result = listAllUser.execute();

        assertEquals(2, result.size());
        verify(userGateway).getAllUsers();
        verifyNoMoreInteractions(userGateway);
    }
}
