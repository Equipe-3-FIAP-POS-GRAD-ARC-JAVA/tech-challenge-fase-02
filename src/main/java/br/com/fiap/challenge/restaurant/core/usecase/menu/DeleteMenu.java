package br.com.fiap.challenge.restaurant.core.usecase.menu;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.usecase.base.UnitUseCase;

public interface DeleteMenu extends UnitUseCase<UUID> {
    void execute(UUID menuId);
}