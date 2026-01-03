package br.com.fiap.challenge.restautant.infra.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class State {

    private String state;

    protected State() {
    }

    public State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static State of(String state) {
        return new State(state);
    }
}