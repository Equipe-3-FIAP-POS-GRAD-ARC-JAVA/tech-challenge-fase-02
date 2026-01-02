package br.com.fiap.challenge.restautant.core.usecase.foodType;

import java.util.List;

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.usecase.NullaryUseCase;

public interface ListAllFoodType extends NullaryUseCase<List<FoodTypeDto>> {
    List<FoodTypeDto> execute();
}