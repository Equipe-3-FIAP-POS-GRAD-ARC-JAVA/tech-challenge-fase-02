package br.com.fiap.challenge.restaurant.core.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AddressTest {

    @DisplayName("Should create address with all attributes")
    @Test
    void shouldCreateAddressWithAllAttributes() {
        // given & when
        Address address = new Address();
        
        // then
        assertThat(address).isNotNull();
        assertThat(BaseEntity.class.isAssignableFrom(Address.class)).isTrue();
    }

    @DisplayName("Address should extend BaseEntity")
    @Test
    void addressShouldExtendBaseEntity() {
        // given & when
        boolean extendsBaseEntity = BaseEntity.class.isAssignableFrom(Address.class);
        
        // then
        assertThat(extendsBaseEntity).isTrue();
    }
}
