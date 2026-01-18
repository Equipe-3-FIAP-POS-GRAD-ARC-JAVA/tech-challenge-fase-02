package br.com.fiap.challenge.restaurant.core.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("Should create menu with all attributes")
    @Test
    void shouldCreateMenuWithAllAttributes() {
        // given & when
        Menu menu = new Menu();
        
        // then
        assertThat(menu).isNotNull();
        assertThat(BaseEntity.class.isAssignableFrom(Menu.class)).isTrue();
    }

    @DisplayName("Menu should extend BaseEntity")
    @Test
    void menuShouldExtendBaseEntity() {
        // given & when
        boolean extendsBaseEntity = BaseEntity.class.isAssignableFrom(Menu.class);
        
        // then
        assertThat(extendsBaseEntity).isTrue();
    }
}
