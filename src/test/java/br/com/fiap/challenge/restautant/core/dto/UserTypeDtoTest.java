package br.com.fiap.challenge.restautant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class UserTypeDtoTest {

    @Test
    void shouldCreateUserTypeDto() {
        UUID id = UUID.randomUUID();
        String name = "Dono de Restaurante";

        UserTypeDto dto = new UserTypeDto(id, name);

        assertThat(dto.id()).isEqualTo(id);
        assertThat(dto.name()).isEqualTo(name);
    }

    @Test
    void shouldHaveEqualsAndHashCode() {
        UUID id = UUID.randomUUID();
        UserTypeDto dto1 = new UserTypeDto(id, "Cliente");
        UserTypeDto dto2 = new UserTypeDto(id, "Cliente");

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    void shouldHaveToString() {
        UUID id = UUID.randomUUID();
        UserTypeDto dto = new UserTypeDto(id, "Cliente");

        assertThat(dto.toString()).contains("UserTypeDto").contains("Cliente");
    }
}
