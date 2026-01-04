package br.com.fiap.challenge.restaurant.core.usecase.food;

import br.com.fiap.challenge.restaurant.core.dto.FoodDto;
import br.com.fiap.challenge.restaurant.core.dto.FoodInput;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface CreatedFood extends UseCase<FoodInput, FoodDto> {

    FoodDto execute(FoodInput foodInput);

}
