package br.com.fiap.challenge.restaurant.core.usecase.menu;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface ListMenuById extends UseCase<UUID, MenuDto> {
    MenuDto execute(UUID menuId);
}