package br.com.fiap.challenge.restaurant.core.usecase.user;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;

@Component
public class ListAllUserImpl implements ListAllUser {

    private final UserGateway userGateway;

    public ListAllUserImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserDto> execute() {
        return userGateway.getAllUsers();
    }
}
