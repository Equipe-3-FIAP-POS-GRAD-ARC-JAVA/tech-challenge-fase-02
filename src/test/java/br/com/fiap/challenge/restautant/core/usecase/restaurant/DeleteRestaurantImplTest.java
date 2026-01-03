package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;

@ExtendWith(MockitoExtension.class)
class DeleteRestaurantImplTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    private DeleteRestaurant useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteRestaurantImpl(restaurantGateway);
    }

    @Test
    void shouldDeleteRestaurant() {
        // given
        UUID id = UUID.randomUUID();

        // when
        useCase.execute(id);

        // then
        verify(restaurantGateway).deleteRestaurant(id);
    }

}