package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.usecase.UseCase;

public interface ListRestaurantById extends UseCase<UUID, RestaurantDto> {
    RestaurantDto execute(UUID restaurantId);
}