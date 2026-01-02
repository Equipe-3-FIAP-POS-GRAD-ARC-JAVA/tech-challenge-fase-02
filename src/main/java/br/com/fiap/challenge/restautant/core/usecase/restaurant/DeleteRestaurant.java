package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.usecase.UnitUseCase;

public interface DeleteRestaurant extends UnitUseCase<UUID> {
    void execute(UUID restaurantId);
}