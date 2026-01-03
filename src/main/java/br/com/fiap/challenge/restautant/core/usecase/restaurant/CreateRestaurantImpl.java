package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;

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