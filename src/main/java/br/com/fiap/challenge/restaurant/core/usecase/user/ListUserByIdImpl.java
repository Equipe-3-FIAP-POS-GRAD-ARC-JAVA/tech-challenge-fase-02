package br.com.fiap.challenge.restaurant.core.usecase.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;

@Component
public class ListUserByIdImpl implements ListUserById {

    private final UserGateway userGateway;

    public ListUserByIdImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserDto execute(UUID userId) {
        return userGateway.getUserById(userId);
    }
}
