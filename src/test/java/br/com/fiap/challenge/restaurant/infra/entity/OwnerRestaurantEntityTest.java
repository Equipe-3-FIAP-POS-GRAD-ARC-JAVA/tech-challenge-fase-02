package br.com.fiap.challenge.restaurant.infra.entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class OwnerRestaurantEntityTest {

    @Test
    void shouldCreateOwnerRestaurantWithConstructor() {
        User owner = mock(User.class);
        Restaurant restaurant = mock(Restaurant.class);

        OwnerRestaurant rel = new OwnerRestaurant(owner, restaurant);

        assertNull(rel.getId());
        assertEquals(owner, rel.getOwner());
        assertEquals(restaurant, rel.getRestaurant());
    }

    @Test
    void shouldAllowChangeOwnerAndRestaurantWithSetters() {
        User owner1 = mock(User.class);
        User owner2 = mock(User.class);

        Restaurant restaurant1 = mock(Restaurant.class);
        Restaurant restaurant2 = mock(Restaurant.class);

        OwnerRestaurant rel = new OwnerRestaurant(owner1, restaurant1);

        rel.setOwner(owner2);
        rel.setRestaurant(restaurant2);

        assertEquals(owner2, rel.getOwner());
        assertEquals(restaurant2, rel.getRestaurant());
    }

    @Test
    void shouldExposeOwnerAndRestaurantIdsViaMocks() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        User owner = mock(User.class);
        when(owner.getId()).thenReturn(ownerId);

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurant.getId()).thenReturn(restaurantId);

        OwnerRestaurant rel = new OwnerRestaurant(owner, restaurant);

        assertEquals(ownerId, rel.getOwner().getId());
        assertEquals(restaurantId, rel.getRestaurant().getId());
    }
}
