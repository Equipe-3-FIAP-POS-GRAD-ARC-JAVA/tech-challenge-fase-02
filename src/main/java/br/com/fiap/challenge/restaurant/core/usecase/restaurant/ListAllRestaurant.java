package br.com.fiap.challenge.restaurant.core.usecase.restaurant;

import java.util.List;

import br.com.fiap.challenge.restaurant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restaurant.core.usecase.base.NullaryUseCase;

public interface ListAllRestaurant extends NullaryUseCase<List<RestaurantDto>> {
    List<RestaurantDto> execute();
}