package br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant;

import java.util.UUID;

public interface UnlinkOwnerRestaurant {
    void execute(UUID ownerId, UUID restaurantId);
}
