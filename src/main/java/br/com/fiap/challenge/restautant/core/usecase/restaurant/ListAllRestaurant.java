package br.com.fiap.challenge.restautant.core.usecase.restaurant;

import java.util.List;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.usecase.base.NullaryUseCase;

public interface ListAllRestaurant extends NullaryUseCase<List<RestaurantDto>> {
    List<RestaurantDto> execute();
}