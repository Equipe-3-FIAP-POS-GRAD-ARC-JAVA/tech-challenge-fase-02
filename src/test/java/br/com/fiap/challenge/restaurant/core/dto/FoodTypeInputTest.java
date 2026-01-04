package br.com.fiap.challenge.restaurant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodTypeInputTest {

    @Test
    @DisplayName("Should create FoodTypeInput with all fields")
    void shouldCreateFoodTypeInputWithAllFields() {
        String typeFood = "Mexican";

        FoodTypeInput input = new FoodTypeInput(null, typeFood);

        assertThat(input.typeFood()).isEqualTo(typeFood);
    }
}
