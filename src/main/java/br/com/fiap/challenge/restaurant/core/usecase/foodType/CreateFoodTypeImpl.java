package br.com.fiap.challenge.restaurant.core.usecase.foodType;

import br.com.fiap.challenge.restaurant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restaurant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restaurant.core.gateway.FoodTypeGateway;

public class CreateFoodTypeImpl implements CreateFoodType {

    private final FoodTypeGateway foodTypeGateway;

    public CreateFoodTypeImpl(FoodTypeGateway foodTypeGateway) {
        this.foodTypeGateway = foodTypeGateway;
    }

    @Override
    public FoodTypeDto execute(FoodTypeInput foodTypeInput) {
        return foodTypeGateway.createFoodType(foodTypeInput);
    }

}