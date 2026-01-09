package br.com.fiap.challenge.restaurant.core.usecase.user;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.dto.UserInput;
import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;

@Component
public class CreateUserImpl implements CreateUser {

    private final UserGateway userGateway;

    public CreateUserImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserDto execute(UserInput input) {
        if (userGateway.existsByEmail(input.email())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userGateway.existsByLogin(input.login())) {
            throw new IllegalArgumentException("Login already exists");
        }
        return userGateway.createUser(input);
    }


}
