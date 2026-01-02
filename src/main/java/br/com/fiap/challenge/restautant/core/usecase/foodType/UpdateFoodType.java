package br.com.fiap.challenge.restautant.core.usecase.foodType;

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restautant.core.usecase.UseCase;

public interface UpdateFoodType extends UseCase<FoodTypeInput, FoodTypeDto> {
    FoodTypeDto execute(FoodTypeInput foodTypeInput);
}