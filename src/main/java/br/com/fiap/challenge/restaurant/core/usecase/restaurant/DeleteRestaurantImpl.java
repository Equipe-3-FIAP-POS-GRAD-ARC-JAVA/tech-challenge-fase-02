package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.gateway.RestaurantGateway;

public class DeleteRestaurantImpl implements DeleteRestaurant {

    private final RestaurantGateway restaurantGateway;

    public DeleteRestaurantImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public void execute(UUID restaurantId) {
        restaurantGateway.deleteRestaurant(restaurantId);
    }

}