package br.com.fiap.challenge.restaurant.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.dto.UserInput;

public interface UserGateway {

    List<UserDto> getAllUsers();

    UserDto getUserById(UUID userId);

    UserDto createUser(UserInput user);

    UserDto updateUser(UserInput user);

    void deleteUser(UUID userId);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

}
