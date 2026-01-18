package br.com.fiap.challenge.restaurant.core.usecase.ownerRestaurant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantInput;
import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;

class LinkOwnerRestaurantImplTest {

    private OwnerRestaurantGateway gateway;
    private br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.LinkOwnerRestaurantImpl useCase;

    @BeforeEach
    void setUp() {
        gateway = Mockito.mock(OwnerRestaurantGateway.class);
        useCase = new br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.LinkOwnerRestaurantImpl(gateway);
    }

    @Test
    void shouldLinkOwnerRestaurant() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        UUID relationId = UUID.randomUUID();

        OwnerRestaurantInput input = new OwnerRestaurantInput(ownerId, restaurantId);
        OwnerRestaurantDto expected = new OwnerRestaurantDto(relationId, ownerId, restaurantId);

        when(gateway.link(input)).thenReturn(expected);

        OwnerRestaurantDto result = useCase.execute(input);

        assertNotNull(result);
        assertEquals(relationId, result.id());
        assertEquals(ownerId, result.ownerId());
        assertEquals(restaurantId, result.restaurantId());

        verify(gateway).link(input);
        verifyNoMoreInteractions(gateway);
    }
}