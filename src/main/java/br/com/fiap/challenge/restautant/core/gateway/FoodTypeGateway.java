package br.com.fiap.challenge.restautant.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.dto.FoodTypeInput;

public interface FoodTypeGateway {

    List<FoodTypeDto> getAllFoodTypes();
    FoodTypeDto getFoodTypeById(UUID foodTypeId);


    FoodTypeDto createFoodType(FoodTypeInput foodType);

    FoodTypeDto updateFoodType(FoodTypeInput foodType);

    void deleteFoodType(UUID foodTypeId);

}
