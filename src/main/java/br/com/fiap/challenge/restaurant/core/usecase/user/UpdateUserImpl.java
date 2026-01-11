package br.com.fiap.challenge.restaurant.core.usecase.user;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.dto.UserInput;
import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;

@Component
public class UpdateUserImpl implements UpdateUser {

    private final UserGateway userGateway;

    public UpdateUserImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserDto execute(UserInput input) {
        return userGateway.updateUser(input);
    }
}
