package br.com.fiap.challenge.restaurant.core.usecase.user;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.UserDto;

public interface ListUserById {

    UserDto execute(UUID userId);
}
