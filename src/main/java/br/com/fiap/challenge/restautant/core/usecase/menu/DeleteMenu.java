package br.com.fiap.challenge.restautant.core.usecase.menu;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.usecase.UnitUseCase;

public interface DeleteMenu extends UnitUseCase<UUID> {
    void execute(UUID menuId);
}