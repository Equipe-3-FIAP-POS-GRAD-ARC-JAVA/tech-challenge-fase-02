package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restaurant.core.gateway.RestaurantGateway;

@ExtendWith(MockitoExtension.class)
class ListRestaurantByIdImplTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    private ListRestaurantById useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListRestaurantByIdImpl(restaurantGateway);
    }

    @Test
    void shouldListRestaurantById() {
        // given
        UUID id = UUID.randomUUID();
        RestaurantDto expected = new RestaurantDto(id, "Test Restaurant", null);

        when(restaurantGateway.getRestaurantById(id)).thenReturn(expected);

        // when
        RestaurantDto result = useCase.execute(id);

        // then
        assertThat(result).isEqualTo(expected);
        verify(restaurantGateway).getRestaurantById(id);
    }

}