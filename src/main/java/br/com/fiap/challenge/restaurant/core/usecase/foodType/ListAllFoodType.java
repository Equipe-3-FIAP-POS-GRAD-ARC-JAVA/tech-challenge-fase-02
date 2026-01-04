package br.com.fiap.challenge.restaurant.core.usecase.foodType;

import java.util.List;

import br.com.fiap.challenge.restaurant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restaurant.core.usecase.base.NullaryUseCase;

public interface ListAllFoodType extends NullaryUseCase<List<FoodTypeDto>> {
    List<FoodTypeDto> execute();
}