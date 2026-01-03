package br.com.fiap.challenge.restautant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class FoodTypeDtoTest {

    @Test
    void shouldCreateFoodTypeDtoWithAllFields() {
        UUID foodTypeId = UUID.randomUUID();
        String typeFood = "Italian";

        FoodTypeDto dto = new FoodTypeDto(foodTypeId, typeFood);

        assertThat(dto.fooTypeId()).isEqualTo(foodTypeId);  // Note: Assuming 'fooTypeId' is intentional (typo in record)
        assertThat(dto.typeFood()).isEqualTo(typeFood);
    }
}
