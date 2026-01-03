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

import br.com.fiap.challenge.restautant.core.dto.AddressInput;
import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restautant.core.dto.AddressInput;
import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;

@ExtendWith(MockitoExtension.class)
class UpdateRestaurantImplTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    private UpdateRestaurant useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateRestaurantImpl(restaurantGateway);
    }

    @Test
    void shouldUpdateRestaurant() {
        // given
        UUID id = UUID.randomUUID();
        String name = "Updated Restaurant";
        AddressInput address = new AddressInput("Street", "123", "City", "Neighborhood", "Country", "State", "12345");
        RestaurantInput input = new RestaurantInput(id, name, address);

        UUID menuId = UUID.randomUUID();
        List<FoodDto> foods = List.of();
        MenuDto menu = new MenuDto(menuId, id, foods);
        RestaurantDto expected = new RestaurantDto(id, name, menu);

        when(restaurantGateway.updateRestaurant(input)).thenReturn(expected);

        // when
        RestaurantDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(restaurantGateway).updateRestaurant(input);
    }

}
