package br.com.fiap.challenge.restaurant.core.usecase.foodType;

import br.com.fiap.challenge.restaurant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restaurant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface UpdateFoodType extends UseCase<FoodTypeInput, FoodTypeDto> {
    FoodTypeDto execute(FoodTypeInput foodTypeInput);
}