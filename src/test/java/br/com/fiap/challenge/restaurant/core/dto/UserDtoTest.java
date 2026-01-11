package br.com.fiap.challenge.restaurant.core.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;

class UserDtoTest {

    @Test
    void shouldCreateUserDto() {
        UUID id = UUID.randomUUID();

        UserDto dto = new UserDto(
                id,
                "Jose",
                "jose@email.com",
                "jose",
                true,
                Role.CUSTOMER
        );

        assertEquals(id, dto.id());
        assertEquals("Jose", dto.name());
        assertEquals("jose@email.com", dto.email());
        assertEquals("jose", dto.login());
        assertTrue(dto.active());
        assertEquals(Role.CUSTOMER, dto.role());
    }

    @Test
    void shouldAllowInactiveUser() {
        UUID id = UUID.randomUUID();

        UserDto dto = new UserDto(
                id,
                "Maria",
                "maria@email.com",
                "maria",
                false,
                Role.OWNER_RESTAURANT
        );

        assertFalse(dto.active());
        assertEquals(Role.OWNER_RESTAURANT, dto.role());
    }
}
