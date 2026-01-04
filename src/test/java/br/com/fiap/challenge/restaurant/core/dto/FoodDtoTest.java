package br.com.fiap.challenge.restaurant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodDtoTest {

    @Test
    @DisplayName("Should create FoodDto with all fields")
    void shouldCreateFoodDtoWithAllFields() {
        UUID menuId = UUID.randomUUID();
        UUID foodId = UUID.randomUUID();
        String name = "Pizza";
        String description = "Delicious pizza";
        UUID foodTypeId = UUID.randomUUID();
        Double price = 25.99;
        String imageURL = "http://image.com";

        FoodDto dto = new FoodDto(menuId, foodId, name, description, foodTypeId, price, imageURL);

        assertThat(dto.menuId()).isEqualTo(menuId);
        assertThat(dto.foodId()).isEqualTo(foodId);
        assertThat(dto.name()).isEqualTo(name);
        assertThat(dto.description()).isEqualTo(description);
        assertThat(dto.foodTypeId()).isEqualTo(foodTypeId);
        assertThat(dto.price()).isEqualTo(price);
        assertThat(dto.imageURL()).isEqualTo(imageURL);
    }
}
