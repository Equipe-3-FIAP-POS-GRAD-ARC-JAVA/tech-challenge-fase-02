package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

import br.com.fiap.challenge.restaurant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface CreateRestaurant extends UseCase<RestaurantInput, RestaurantDto> {
    RestaurantDto execute(RestaurantInput restaurantInput);
}