package br.com.fiap.challenge.restaurant.core.usecase.ownerRestaurant;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;
import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.ListOwnerRestaurantByRestaurantImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ListOwnerRestaurantByRestaurantImplTest {

    private OwnerRestaurantGateway gateway;
    private ListOwnerRestaurantByRestaurantImpl useCase;

    @BeforeEach
    void setUp() {
        gateway = Mockito.mock(OwnerRestaurantGateway.class);
        useCase = new ListOwnerRestaurantByRestaurantImpl(gateway);
    }

    @Test
    void shouldListByRestaurant() {
        UUID restaurantId = UUID.randomUUID();

        List<OwnerRestaurantDto> expected = List.of(
                new OwnerRestaurantDto(UUID.randomUUID(), UUID.randomUUID(), restaurantId),
                new OwnerRestaurantDto(UUID.randomUUID(), UUID.randomUUID(), restaurantId)
        );

        when(gateway.listByRestaurant(restaurantId)).thenReturn(expected);

        List<OwnerRestaurantDto> result = useCase.execute(restaurantId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(restaurantId, result.get(0).restaurantId());

        verify(gateway).listByRestaurant(restaurantId);
        verifyNoMoreInteractions(gateway);
    }
}