package br.com.fiap.challenge.restautant.core.usecase.userType;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.UserTypeDto;
import br.com.fiap.challenge.restautant.core.gateway.UserTypeGateway;

public class ListUserTypeByIdImpl implements ListUserTypeById {

    private final UserTypeGateway userTypeGateway;

    public ListUserTypeByIdImpl(UserTypeGateway userTypeGateway) {
        this.userTypeGateway = userTypeGateway;
    }

    @Override
    public UserTypeDto execute(UUID userTypeId) {
        return userTypeGateway.getUserTypeById(userTypeId);
    }
}