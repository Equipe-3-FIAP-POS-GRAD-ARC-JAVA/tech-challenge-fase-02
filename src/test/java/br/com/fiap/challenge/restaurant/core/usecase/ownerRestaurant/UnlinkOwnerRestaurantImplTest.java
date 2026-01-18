package br.com.fiap.challenge.restaurant.core.usecase.ownerRestaurant;

import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;
import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.UnlinkOwnerRestaurantImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class UnlinkOwnerRestaurantImplTest {

    private OwnerRestaurantGateway gateway;
    private UnlinkOwnerRestaurantImpl useCase;

    @BeforeEach
    void setUp() {
        gateway = Mockito.mock(OwnerRestaurantGateway.class);
        useCase = new UnlinkOwnerRestaurantImpl(gateway);
    }

    @Test
    void shouldUnlinkOwnerRestaurant() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        useCase.execute(ownerId, restaurantId);

        verify(gateway).unlink(ownerId, restaurantId);
        verifyNoMoreInteractions(gateway);
    }
}