package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;

@ExtendWith(MockitoExtension.class)
class ListAllRestaurantImplTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    private ListAllRestaurant useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListAllRestaurantImpl(restaurantGateway);
    }

    @Test
    void shouldListAllRestaurant() {
        // given
        List<RestaurantDto> expected = List.of(new RestaurantDto(UUID.randomUUID(), "Test Restaurant", null));

        when(restaurantGateway.getAllRestaurants()).thenReturn(expected);

        // when
        List<RestaurantDto> result = useCase.execute();

        // then
        assertThat(result).isEqualTo(expected);
        verify(restaurantGateway).getAllRestaurants();
    }

}