package br.com.fiap.challenge.restautant.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.FoodTypeInput;

public interface FoodTypeGateway {

    List<FoodTypeInput> getAllFoodTypes();
    FoodTypeInput getFoodTypeById(UUID foodTypeId);


    FoodTypeInput createFoodType(FoodTypeInput foodType);

    FoodTypeInput updateFoodType(FoodTypeInput foodType);

    void deleteFoodType(UUID foodTypeId);

}
