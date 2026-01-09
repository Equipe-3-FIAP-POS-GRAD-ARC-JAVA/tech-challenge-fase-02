package br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;

import java.util.List;
import java.util.UUID;

public class ListOwnerRestaurantByOwnerImpl implements ListOwnerRestaurantByOwner{
    private final OwnerRestaurantGateway gateway;

    public ListOwnerRestaurantByOwnerImpl(OwnerRestaurantGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<OwnerRestaurantDto> execute(UUID ownerId) {
        return gateway.listByOwner(ownerId);
    }
}
