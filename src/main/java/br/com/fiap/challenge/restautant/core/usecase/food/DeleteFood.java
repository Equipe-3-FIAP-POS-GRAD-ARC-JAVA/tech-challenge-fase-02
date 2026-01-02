package br.com.fiap.challenge.restautant.core.usecase.food;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.usecase.UnitUseCase;

public interface DeleteFood extends UnitUseCase<UUID> {
    void execute(UUID foodId);
}
