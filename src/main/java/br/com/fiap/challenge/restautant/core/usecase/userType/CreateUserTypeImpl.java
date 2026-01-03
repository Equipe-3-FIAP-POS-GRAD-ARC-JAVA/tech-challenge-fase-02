package br.com.fiap.challenge.restautant.core.usecase.userType;

import br.com.fiap.challenge.restautant.core.dto.UserTypeDto;
import br.com.fiap.challenge.restautant.core.dto.UserTypeInput;
import br.com.fiap.challenge.restautant.core.gateway.UserTypeGateway;

public class CreateUserTypeImpl implements CreateUserType {

    private final UserTypeGateway userTypeGateway;

    public CreateUserTypeImpl(UserTypeGateway userTypeGateway) {
        this.userTypeGateway = userTypeGateway;
    }

    @Override
    public UserTypeDto execute(UserTypeInput userTypeInput) {
        return userTypeGateway.createUserType(userTypeInput);
    }
}