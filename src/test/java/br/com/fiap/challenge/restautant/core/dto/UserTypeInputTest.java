package br.com.fiap.challenge.restautant.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UserTypeInputTest {

    @Test
    void shouldCreateUserTypeInput() {
        String name = "Cliente";

        UserTypeInput input = new UserTypeInput(null, name);

        assertThat(input.name()).isEqualTo(name);
    }

    @Test
    void shouldHaveEqualsAndHashCode() {
        UserTypeInput input1 = new UserTypeInput(null, "Dono");
        UserTypeInput input2 = new UserTypeInput(null, "Dono");

        assertThat(input1).isEqualTo(input2);
        assertThat(input1.hashCode()).isEqualTo(input2.hashCode());
    }

    @Test
    void shouldHaveToString() {
        UserTypeInput input = new UserTypeInput(null, "Cliente");

        assertThat(input.toString()).contains("UserTypeInput").contains("Cliente");
    }
}
