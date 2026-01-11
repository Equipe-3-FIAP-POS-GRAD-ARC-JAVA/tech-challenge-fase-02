package br.com.fiap.challenge.restaurant.core.usecase.user;

import java.util.List;

import br.com.fiap.challenge.restaurant.core.dto.UserDto;

public interface ListAllUser {

    List<UserDto> execute();
}
