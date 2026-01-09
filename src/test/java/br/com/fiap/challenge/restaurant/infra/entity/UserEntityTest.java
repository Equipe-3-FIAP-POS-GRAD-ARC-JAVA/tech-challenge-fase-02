package br.com.fiap.challenge.restaurant.infra.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;

class UserEntityTest {

    @Test
    void shouldCreateUserEntityWithConstructor() {

        User user = new User(
                "Mariana",
                "mariana@email.com",
                "mariana",
                "123",
                true,
                Role.OWNER_RESTAURANT
        );

        assertEquals("Mariana", user.getName());
        assertEquals("mariana@email.com", user.getEmail());
        assertEquals("mariana", user.getLogin());
        assertTrue(user.isActive());
        assertEquals(Role.OWNER_RESTAURANT, user.getRole());
    }

    @Test
    void shouldAllowChangeUserFieldsUsingSetters() {

        User user = new User(
                "Jose",
                "jose@email.com",
                "jose",
                "321",
                false,
                Role.CUSTOMER
        );

        user.setName("Jose Maria");
        user.setEmail("josemaria@email.com");
        user.setLogin("jose.maria");
        user.setActive(true);
        user.setRole(Role.ADMIN);

        assertEquals("Jose Maria", user.getName());
        assertEquals("josemaria@email.com", user.getEmail());
        assertEquals("jose.maria", user.getLogin());
        assertTrue(user.isActive());
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void shouldCreateUserWithInactiveStatus() {

        User user = new User(
                "Maria",
                "maria@email.com",
                "maria",
                "123",
                false,
                Role.CUSTOMER
        );

        assertFalse(user.isActive());
        assertEquals(Role.CUSTOMER, user.getRole());
    }
}
