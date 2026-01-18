package br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;

import java.util.List;
import java.util.UUID;

public class ListOwnerRestaurantByRestaurantImpl implements ListOwnerRestaurantByRestaurant {

    private final OwnerRestaurantGateway gateway;

    public ListOwnerRestaurantByRestaurantImpl(OwnerRestaurantGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<OwnerRestaurantDto> execute(UUID restaurantId) {
        return gateway.listByRestaurant(restaurantId);
    }
}
