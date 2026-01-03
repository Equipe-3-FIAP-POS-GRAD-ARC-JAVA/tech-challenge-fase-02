package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;

public class ListRestaurantByIdImpl implements ListRestaurantById {

    private final RestaurantGateway restaurantGateway;

    public ListRestaurantByIdImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public RestaurantDto execute(UUID restaurantId) {
        return restaurantGateway.getRestaurantById(restaurantId);
    }

}