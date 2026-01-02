package br.com.fiap.challenge.restautant.core.usecase.foodType;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.usecase.UnitUseCase;

public interface DeleteFoodType extends UnitUseCase<UUID> {
    void execute(UUID foodTypeId);
}