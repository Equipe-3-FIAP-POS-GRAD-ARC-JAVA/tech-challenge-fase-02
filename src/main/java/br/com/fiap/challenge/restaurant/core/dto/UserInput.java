package br.com.fiap.challenge.restaurant.core.dto;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;

import java.util.UUID;

public record UserInput(
        UUID id,
        String name,
        String email,
        String login,
        String password,
        boolean active,
        Role role
) {
}
