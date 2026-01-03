package br.com.fiap.challenge.restautant.core.usecase.foodType;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.gateway.FoodTypeGateway;

public class ListFoodTypeByIdImpl implements ListFoodTypeById {

    private final FoodTypeGateway foodTypeGateway;

    public ListFoodTypeByIdImpl(FoodTypeGateway foodTypeGateway) {
        this.foodTypeGateway = foodTypeGateway;
    }

    @Override
    public FoodTypeDto execute(UUID foodTypeId) {
        return foodTypeGateway.getFoodTypeById(foodTypeId);
    }

}