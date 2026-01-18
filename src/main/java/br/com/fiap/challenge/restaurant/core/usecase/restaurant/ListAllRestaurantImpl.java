package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

import java.util.List;

import br.com.fiap.challenge.restaurant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restaurant.core.gateway.RestaurantGateway;

public class ListAllRestaurantImpl implements ListAllRestaurant {

    private final RestaurantGateway restaurantGateway;

    public ListAllRestaurantImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public List<RestaurantDto> execute() {
        return restaurantGateway.getAllRestaurants();
    }

}