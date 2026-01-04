package br.com.fiap.challenge.restaurant.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateEntityTest {

    @Test
    @DisplayName("Should create State with state name")
    void shouldCreateStateWithStateName() {
        // Given
        String stateName = "SP";
        
        // When
        State state = new State(stateName);
        
        // Then
        assertThat(state.getState()).isEqualTo(stateName);
    }

    @Test
    @DisplayName("Should create State using factory method")
    void shouldCreateStateUsingFactoryMethod() {
        // Given
        String stateName = "RJ";
        
        // When
        State state = State.of(stateName);
        
        // Then
        assertThat(state).isNotNull();
        assertThat(state.getState()).isEqualTo(stateName);
    }

    @Test
    @DisplayName("Should update state name")
    void shouldUpdateStateName() {
        // Given
        State state = new State("SP");
        
        // When
        state.setState("RJ");
        
        // Then
        assertThat(state.getState()).isEqualTo("RJ");
    }
}
