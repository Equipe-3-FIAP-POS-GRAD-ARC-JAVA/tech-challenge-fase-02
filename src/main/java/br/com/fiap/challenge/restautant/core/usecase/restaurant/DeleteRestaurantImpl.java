package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;

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