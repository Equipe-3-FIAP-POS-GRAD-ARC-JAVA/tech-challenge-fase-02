package br.com.fiap.challenge.restautant.core.usecase.userType;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.UserTypeDto;
import br.com.fiap.challenge.restautant.core.usecase.base.UseCase;

public interface ListUserTypeById extends UseCase<UUID, UserTypeDto> {
}