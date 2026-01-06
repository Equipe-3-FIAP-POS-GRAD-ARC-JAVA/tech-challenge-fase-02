package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.usecase.base.UnitUseCase;

public interface DeleteRestaurant extends UnitUseCase<UUID> {
    void execute(UUID restaurantId);
}