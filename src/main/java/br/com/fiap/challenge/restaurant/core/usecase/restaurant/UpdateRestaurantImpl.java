package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

import br.com.fiap.challenge.restaurant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restaurant.core.gateway.RestaurantGateway;

public class UpdateRestaurantImpl implements UpdateRestaurant {

    private final RestaurantGateway restaurantGateway;

    public UpdateRestaurantImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public RestaurantDto execute(RestaurantInput restaurantInput) {
        return restaurantGateway.updateRestaurant(restaurantInput);
    }

}