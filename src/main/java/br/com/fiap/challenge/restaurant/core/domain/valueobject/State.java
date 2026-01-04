package br.com.fiap.challenge.restaurant.core.domain.valueobject;

public record State(String state) {

    public static State of(String state) {
        return new State(state);
    }
}
