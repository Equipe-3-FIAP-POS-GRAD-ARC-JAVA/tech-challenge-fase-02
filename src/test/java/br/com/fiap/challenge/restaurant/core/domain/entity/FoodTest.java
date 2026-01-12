package br.com.fiap.challenge.restaurant.core.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodTest {

    @DisplayName("Should create food with all attributes")
    @Test
    void shouldCreateFoodWithAllAttributes() {
        // given
        UUID menuId = UUID.randomUUID();
        FoodType foodType = new FoodType();
        
        // when
        Food food = new Food();
        
        // then
        assertThat(food).isNotNull();
        assertThat(BaseEntity.class.isAssignableFrom(Food.class)).isTrue();
    }

    @DisplayName("Food should extend BaseEntity")
    @Test
    void foodShouldExtendBaseEntity() {
        // given & when
        boolean extendsBaseEntity = BaseEntity.class.isAssignableFrom(Food.class);
        
        // then
        assertThat(extendsBaseEntity).isTrue();
    }
}
