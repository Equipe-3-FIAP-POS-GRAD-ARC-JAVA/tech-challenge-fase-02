package br.com.fiap.challenge.restautant.core.usecase.menu;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.usecase.UseCase;

public interface ListMenuById extends UseCase<UUID, MenuDto> {
    MenuDto execute(UUID menuId);
}