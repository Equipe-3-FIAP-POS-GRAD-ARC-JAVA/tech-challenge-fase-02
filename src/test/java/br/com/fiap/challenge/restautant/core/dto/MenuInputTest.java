package br.com.fiap.challenge.restautant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class MenuInputTest {

    @Test
    void shouldCreateMenuInputWithAllFields() {
        UUID restaurantId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();    

        MenuInput input = new MenuInput(menuId, restaurantId);

        assertThat(input.restaurantId()).isEqualTo(restaurantId);
    }
}
