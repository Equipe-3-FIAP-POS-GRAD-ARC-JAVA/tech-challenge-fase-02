package br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantInput;
import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;

public class LinkOwnerRestaurantImpl implements LinkOwnerRestaurant {

    private final OwnerRestaurantGateway gateway;

    public LinkOwnerRestaurantImpl(OwnerRestaurantGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public OwnerRestaurantDto execute(OwnerRestaurantInput input) {
        return gateway.link(input);
    }
}
