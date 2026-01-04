package br.com.fiap.challenge.restaurant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestaurantInputTest {

    @Test
    @DisplayName("Should create RestaurantInput with all fields")
    void shouldCreateRestaurantInputWithAllFields() {
        String name = "New Restaurant";
        AddressInput address = new AddressInput("Street", "123", "City", "Neighborhood", "Country", "State", "12345");

        RestaurantInput input = new RestaurantInput(null, name, address);

        assertThat(input.name()).isEqualTo(name);
        assertThat(input.address()).isEqualTo(address);
    }
}
