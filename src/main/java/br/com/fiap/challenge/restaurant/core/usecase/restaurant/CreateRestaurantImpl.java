package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

import br.com.fiap.challenge.restaurant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restaurant.core.gateway.RestaurantGateway;

public class CreateRestaurantImpl implements CreateRestaurant {

    private final RestaurantGateway restaurantGateway;

    public CreateRestaurantImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public RestaurantDto execute(RestaurantInput restaurantInput) {
        return restaurantGateway.createRestaurant(restaurantInput);
    }

}