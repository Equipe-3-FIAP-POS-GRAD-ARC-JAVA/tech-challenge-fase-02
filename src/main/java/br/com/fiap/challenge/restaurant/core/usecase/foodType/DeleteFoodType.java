package br.com.fiap.challenge.restaurant.core.usecase.foodType;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.usecase.base.UnitUseCase;

public interface DeleteFoodType extends UnitUseCase<UUID> {
    void execute(UUID foodTypeId);
}