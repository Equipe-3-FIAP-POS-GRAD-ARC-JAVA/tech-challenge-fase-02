package br.com.fiap.challenge.restautant.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodTypeEntityTest {

    @Test
    @DisplayName("Should create FoodType with type")
    void shouldCreateFoodTypeWithType() {
        // Given
        String typeFood = "Italian";
        
        // When
        FoodType foodType = new FoodType(typeFood);
        
        // Then
        assertThat(foodType.getTypeFood()).isEqualTo(typeFood);
    }

    @Test
    @DisplayName("Should update food type")
    void shouldUpdateFoodType() {
        // Given
        FoodType foodType = new FoodType("Italian");
        
        // When
        foodType.setTypeFood("Brazilian");
        
        // Then
        assertThat(foodType.getTypeFood()).isEqualTo("Brazilian");
    }

    @Test
    @DisplayName("Should create different FoodType instances")
    void shouldCreateDifferentFoodTypeInstances() {
        // Given
        FoodType type1 = new FoodType("Italian");
        FoodType type2 = new FoodType("Brazilian");
        
        // Then
        assertThat(type1).isNotEqualTo(type2);
        assertThat(type1.getTypeFood()).isNotEqualTo(type2.getTypeFood());
    }
}
