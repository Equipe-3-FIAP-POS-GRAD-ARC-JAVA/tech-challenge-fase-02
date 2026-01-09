package br.com.fiap.challenge.restaurant.core.usecase.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;

@Component
public class DeleteUserImpl implements DeleteUser {

    private final UserGateway userGateway;

    public DeleteUserImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void execute(UUID userId) {
        userGateway.deleteUser(userId);
    }
}
