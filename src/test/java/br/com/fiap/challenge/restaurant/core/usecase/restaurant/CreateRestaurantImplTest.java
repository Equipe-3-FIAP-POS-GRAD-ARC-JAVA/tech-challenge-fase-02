package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

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

import br.com.fiap.challenge.restaurant.core.dto.AddressInput;
import br.com.fiap.challenge.restaurant.core.dto.FoodDto;
import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restaurant.core.gateway.RestaurantGateway;

@ExtendWith(MockitoExtension.class)
class CreateRestaurantImplTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    private CreateRestaurant useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateRestaurantImpl(restaurantGateway);
    }

    @Test
    void shouldCreateRestaurant() {
        // given
        String name = "Restaurant Name";
        AddressInput address = new AddressInput("Street", "123", "City", "Neighborhood", "Country", "State", "12345");
        RestaurantInput input = new RestaurantInput(null, name, address);

        UUID restaurantId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();
        List<FoodDto> foods = List.of();
        MenuDto menu = new MenuDto(menuId, restaurantId, foods);
        List<MenuDto> menus = List.of(menu);
        RestaurantDto expected = new RestaurantDto(restaurantId, name, menus);

        when(restaurantGateway.createRestaurant(input)).thenReturn(expected);

        // when
        RestaurantDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(restaurantGateway).createRestaurant(input);
    }

}