package br.com.fiap.challenge.restaurant.core.domain.valueobject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateTest {

    @Test
    @DisplayName("Should create State with valid state name")
    void shouldCreateStateWithValidStateName() {
        // Given
        String stateName = "SP";
        
        // When
        State state = new State(stateName);
        
        // Then
        assertThat(state.state()).isEqualTo(stateName);
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
        assertThat(state.state()).isEqualTo(stateName);
    }

    @Test
    @DisplayName("Should create different State instances with different values")
    void shouldCreateDifferentStateInstancesWithDifferentValues() {
        // Given
        State state1 = State.of("SP");
        State state2 = State.of("RJ");
        
        // Then
        assertThat(state1).isNotEqualTo(state2);
        assertThat(state1.state()).isNotEqualTo(state2.state());
    }

    @Test
    @DisplayName("Should create equal State instances with same value")
    void shouldCreateEqualStateInstancesWithSameValue() {
        // Given
        State state1 = State.of("SP");
        State state2 = State.of("SP");
        
        // Then
        assertThat(state1).isEqualTo(state2);
    }
}
