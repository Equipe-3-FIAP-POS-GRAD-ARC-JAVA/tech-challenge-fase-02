package br.com.fiap.challenge.restaurant.core.usecase.ownerRestaurant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.ListOwnerRestaurantByOwnerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;

class ListOwnerRestaurantByOwnerImplTest {

    private OwnerRestaurantGateway gateway;
    private ListOwnerRestaurantByOwnerImpl useCase;

    @BeforeEach
    void setUp() {
        gateway = Mockito.mock(OwnerRestaurantGateway.class);
        useCase = new ListOwnerRestaurantByOwnerImpl(gateway);
    }

    @Test
    void shouldListByOwner() {
        UUID ownerId = UUID.randomUUID();

        List<OwnerRestaurantDto> expected = List.of(
                new OwnerRestaurantDto(UUID.randomUUID(), ownerId, UUID.randomUUID()),
                new OwnerRestaurantDto(UUID.randomUUID(), ownerId, UUID.randomUUID())
        );

        when(gateway.listByOwner(ownerId)).thenReturn(expected);

        List<OwnerRestaurantDto> result = useCase.execute(ownerId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(ownerId, result.get(0).ownerId());

        verify(gateway).listByOwner(ownerId);
        verifyNoMoreInteractions(gateway);
    }
}