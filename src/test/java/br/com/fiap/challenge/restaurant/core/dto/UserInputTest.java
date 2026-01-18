package br.com.fiap.challenge.restaurant.core.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;

class UserInputTest {

    @Test
    void shouldCreateUserInput() {
        UUID id = UUID.randomUUID();

        UserInput input = new UserInput(
                id,
                "Jose",
                "jose@email.com",
                "jose",
                "321",
                true,
                Role.CUSTOMER
        );

        assertEquals(id, input.id());
        assertEquals("Jose", input.name());
        assertEquals("jose@email.com", input.email());
        assertEquals("jose", input.login());
        assertEquals("321", input.password());
        assertTrue(input.active());
        assertEquals(Role.CUSTOMER, input.role());
    }

    @Test
    void shouldAllowNullIdWhenCreatingUser() {
        UserInput input = new UserInput(
                null,
                "Maria",
                "maria@email.com",
                "maria",
                "123",
                true,
                Role.OWNER_RESTAURANT
        );

        assertNull(input.id());
        assertEquals("Maria", input.name());
        assertEquals("maria@email.com", input.email());
        assertEquals("maria", input.login());
        assertEquals("123", input.password());
        assertTrue(input.active());
        assertEquals(Role.OWNER_RESTAURANT, input.role());
    }
}
