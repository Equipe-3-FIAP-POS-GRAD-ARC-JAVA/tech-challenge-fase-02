package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;

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