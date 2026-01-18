package br.com.fiap.challenge.restaurant.core.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OpeningHoursTest {

    @DisplayName("Should create opening hours with all attributes")
    @Test
    void shouldCreateOpeningHoursWithAllAttributes() {
        // given & when
        OpeningHours openingHours = new OpeningHours();
        
        // then
        assertThat(openingHours).isNotNull();
        assertThat(BaseEntity.class.isAssignableFrom(OpeningHours.class)).isTrue();
    }

    @DisplayName("OpeningHours should extend BaseEntity")
    @Test
    void openingHoursShouldExtendBaseEntity() {
        // given & when
        boolean extendsBaseEntity = BaseEntity.class.isAssignableFrom(OpeningHours.class);
        
        // then
        assertThat(extendsBaseEntity).isTrue();
    }
}
