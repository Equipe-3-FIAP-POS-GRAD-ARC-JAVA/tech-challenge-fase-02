package br.com.fiap.challenge.restaurant.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantInput;

public interface OwnerRestaurantGateway {
    OwnerRestaurantDto link(OwnerRestaurantInput input);
    void unlink(UUID ownerId, UUID restaurantId);

    List<OwnerRestaurantDto> listByOwner(UUID ownerId);
    List<OwnerRestaurantDto> listByRestaurant(UUID restaurantId);

    boolean exists(UUID ownerId, UUID restaurantId);
}
