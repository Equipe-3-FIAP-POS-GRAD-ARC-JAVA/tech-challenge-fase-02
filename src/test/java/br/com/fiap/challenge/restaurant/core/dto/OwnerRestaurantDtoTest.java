package br.com.fiap.challenge.restaurant.core.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class OwnerRestaurantDtoTest {

    @Test
    void shouldCreateOwnerRestaurantDto() {
        UUID id = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        OwnerRestaurantDto dto = new OwnerRestaurantDto(id, ownerId, restaurantId);

        assertEquals(id, dto.id());
        assertEquals(ownerId, dto.ownerId());
        assertEquals(restaurantId, dto.restaurantId());
    }
}
