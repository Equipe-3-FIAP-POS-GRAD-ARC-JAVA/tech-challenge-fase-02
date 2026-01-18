package br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantInput;

public interface LinkOwnerRestaurant {
    OwnerRestaurantDto execute(OwnerRestaurantInput input);
}
