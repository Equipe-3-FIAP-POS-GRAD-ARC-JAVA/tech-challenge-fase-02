package br.com.fiap.challenge.restautant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MenuInputTest {

    @Test
    void shouldCreateMenuInputWithAllFields() {
        String restaurant = "Restaurant Name";

        MenuInput input = new MenuInput(null, restaurant);

        assertThat(input.restaurant()).isEqualTo(restaurant);
    }
}
