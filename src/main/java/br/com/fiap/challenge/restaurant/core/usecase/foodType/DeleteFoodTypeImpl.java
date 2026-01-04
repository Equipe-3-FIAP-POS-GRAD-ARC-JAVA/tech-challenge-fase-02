package br.com.fiap.challenge.restaurant.core.usecase.foodType;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.gateway.FoodTypeGateway;

public class DeleteFoodTypeImpl implements DeleteFoodType {

    private final FoodTypeGateway foodTypeGateway;

    public DeleteFoodTypeImpl(FoodTypeGateway foodTypeGateway) {
        this.foodTypeGateway = foodTypeGateway;
    }

    @Override
    public void execute(UUID foodTypeId) {
        foodTypeGateway.deleteFoodType(foodTypeId);
    }

}