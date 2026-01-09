package br.com.fiap.challenge.restaurant.core.dto;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;

import java.util.UUID;

public record UserDto(
        UUID id,
        String name,
        String email,
        String login,
        boolean active,
        Role role
) {
}
