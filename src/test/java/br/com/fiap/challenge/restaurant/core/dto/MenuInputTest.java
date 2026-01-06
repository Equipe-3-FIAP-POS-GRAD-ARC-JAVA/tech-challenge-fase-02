package br.com.fiap.challenge.restaurant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuInputTest {

    @Test
    @DisplayName("Should create MenuInput with all fields")
    void shouldCreateMenuInputWithAllFields() {
        UUID restaurantId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();    

        MenuInput input = new MenuInput(menuId, restaurantId);

        assertThat(input.restaurantId()).isEqualTo(restaurantId);
    }
}
