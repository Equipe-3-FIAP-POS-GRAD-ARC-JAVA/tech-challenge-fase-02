package br.com.fiap.challenge.restaurant.core.usecase.food;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.usecase.base.UnitUseCase;

public interface DeleteFood extends UnitUseCase<UUID> {
    void execute(UUID foodId);
}
