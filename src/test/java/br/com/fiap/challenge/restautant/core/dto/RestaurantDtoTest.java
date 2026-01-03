package br.com.fiap.challenge.restautant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestaurantDtoTest {

    @Test
    @DisplayName("Should create RestaurantDto with all fields")
    void shouldCreateRestaurantDtoWithAllFields() {
        UUID restaurantId = UUID.randomUUID();
        String name = "Italian Place";
        UUID menuId = UUID.randomUUID();
        UUID restaurantIdForMenu = UUID.randomUUID();
        FoodDto food = new FoodDto(UUID.randomUUID(), UUID.randomUUID(), "Risotto", "Creamy risotto", UUID.randomUUID(), 18.00, "http://image5.com");
        MenuDto menu = new MenuDto(menuId, restaurantIdForMenu, List.of(food));
        List<MenuDto> menus = List.of(menu);

        RestaurantDto dto = new RestaurantDto(restaurantId, name, menus);

        assertThat(dto.restaurantId()).isEqualTo(restaurantId);
        assertThat(dto.name()).isEqualTo(name);
        assertThat(dto.menus()).isEqualTo(menus);
    }
}
