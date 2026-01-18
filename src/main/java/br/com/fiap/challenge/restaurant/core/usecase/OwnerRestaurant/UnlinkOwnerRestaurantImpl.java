package br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant;

import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;

import java.util.UUID;

public class UnlinkOwnerRestaurantImpl implements UnlinkOwnerRestaurant{
    private final OwnerRestaurantGateway gateway;

    public UnlinkOwnerRestaurantImpl(OwnerRestaurantGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void execute(UUID ownerId, UUID restaurantId) {
        gateway.unlink(ownerId, restaurantId);
    }
}
