package br.com.fiap.challenge.restaurant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class FoodInputTest {

    @Test
    @DisplayName("Should create FoodInput with all fields")
    void shouldCreateFoodInputWithAllFields() {
        UUID menuId = UUID.randomUUID();
        String name = "Burger";
        String description = "Tasty burger";
        UUID foodTypeId = UUID.randomUUID();
        Double price = 15.50;
        String imageURL = "http://image2.com";

        FoodInput input = new FoodInput(null, menuId, name, description, foodTypeId, price, imageURL);

        assertThat(input.menuId()).isEqualTo(menuId);
        assertThat(input.name()).isEqualTo(name);
        assertThat(input.description()).isEqualTo(description);
        assertThat(input.foodTypeId()).isEqualTo(foodTypeId);
        assertThat(input.price()).isEqualTo(price);
        assertThat(input.imageURL()).isEqualTo(imageURL);
    }
}
