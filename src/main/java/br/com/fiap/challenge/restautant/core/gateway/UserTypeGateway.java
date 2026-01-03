package br.com.fiap.challenge.restautant.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.UserTypeDto;
import br.com.fiap.challenge.restautant.core.dto.UserTypeInput;

public interface UserTypeGateway {

    UserTypeDto createUserType(UserTypeInput userTypeInput);

    List<UserTypeDto> getAllUserTypes();

    void deleteUserType(UUID userTypeId);

    UserTypeDto getUserTypeById(UUID userTypeId);

    UserTypeDto updateUserType(UserTypeInput userTypeInput);
}