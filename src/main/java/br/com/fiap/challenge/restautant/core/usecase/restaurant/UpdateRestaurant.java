package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restautant.core.usecase.UseCase;

public interface UpdateRestaurant extends UseCase<RestaurantInput, RestaurantDto> {
    RestaurantDto execute(RestaurantInput restaurantInput);
}