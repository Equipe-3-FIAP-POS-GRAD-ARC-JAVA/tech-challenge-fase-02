package br.com.fiap.challenge.restautant.core.usecase.userType;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.gateway.UserTypeGateway;

public class DeleteUserTypeImpl implements DeleteUserType {

    private final UserTypeGateway userTypeGateway;

    public DeleteUserTypeImpl(UserTypeGateway userTypeGateway) {
        this.userTypeGateway = userTypeGateway;
    }

    @Override
    public void execute(UUID userTypeId) {
        userTypeGateway.deleteUserType(userTypeId);
    }
}