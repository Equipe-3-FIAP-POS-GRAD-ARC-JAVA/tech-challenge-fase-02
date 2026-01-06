package br.com.fiap.challenge.restaurant.core.usecase.foodType;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface ListFoodTypeById extends UseCase<UUID, FoodTypeDto> {
    FoodTypeDto execute(UUID foodTypeId);
}