package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface ListRestaurantById extends UseCase<UUID, RestaurantDto> {
    RestaurantDto execute(UUID restaurantId);
}