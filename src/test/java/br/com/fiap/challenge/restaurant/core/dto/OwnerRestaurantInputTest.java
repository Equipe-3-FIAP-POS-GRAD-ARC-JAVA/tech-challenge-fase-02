package br.com.fiap.challenge.restaurant.core.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class OwnerRestaurantInputTest {

    @Test
    void shouldCreateOwnerRestaurantInput() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        OwnerRestaurantInput input = new OwnerRestaurantInput(ownerId, restaurantId);

        assertEquals(ownerId, input.ownerId());
        assertEquals(restaurantId, input.restaurantId());
    }
}
