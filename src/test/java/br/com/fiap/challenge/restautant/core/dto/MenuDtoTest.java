package br.com.fiap.challenge.restautant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class MenuDtoTest {

    @Test
    void shouldCreateMenuDtoWithAllFields() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        FoodDto food1 = new FoodDto(UUID.randomUUID(), UUID.randomUUID(), "Pasta", "Creamy pasta", UUID.randomUUID(), 20.00, "http://image3.com");
        FoodDto food2 = new FoodDto(UUID.randomUUID(), UUID.randomUUID(), "Salad", "Fresh salad", UUID.randomUUID(), 10.00, "http://image4.com");
        List<FoodDto> foods = List.of(food1, food2);

        MenuDto dto = new MenuDto(menuId, restaurantId, foods);

        assertThat(dto.menuId()).isEqualTo(menuId);
        assertThat(dto.restaurantId()).isEqualTo(restaurantId);
        assertThat(dto.foods()).isEqualTo(foods);
    }
}
