package br.com.fiap.challenge.restaurant.core.usecase.user;

import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.dto.UserInput;

public interface CreateUser {

    UserDto execute(UserInput input);
}
