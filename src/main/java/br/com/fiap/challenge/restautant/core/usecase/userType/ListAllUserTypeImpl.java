package br.com.fiap.challenge.restautant.core.usecase.userType;

import java.util.List;

import br.com.fiap.challenge.restautant.core.dto.UserTypeDto;
import br.com.fiap.challenge.restautant.core.gateway.UserTypeGateway;

public class ListAllUserTypeImpl implements ListAllUserType {

    private final UserTypeGateway userTypeGateway;

    public ListAllUserTypeImpl(UserTypeGateway userTypeGateway) {
        this.userTypeGateway = userTypeGateway;
    }

    @Override
    public List<UserTypeDto> execute() {
        return userTypeGateway.getAllUserTypes();
    }
}