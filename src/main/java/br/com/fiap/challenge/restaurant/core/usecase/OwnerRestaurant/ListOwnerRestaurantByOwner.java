package br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;

import java.util.List;
import java.util.UUID;

public interface ListOwnerRestaurantByOwner {
    List<OwnerRestaurantDto> execute(UUID ownerId);
}
