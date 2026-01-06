package br.com.fiap.challenge.restaurant.core.usecase.foodType;

import java.util.List;

import br.com.fiap.challenge.restaurant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restaurant.core.gateway.FoodTypeGateway;

public class ListAllFoodTypeImpl implements ListAllFoodType {

    private final FoodTypeGateway foodTypeGateway;

    public ListAllFoodTypeImpl(FoodTypeGateway foodTypeGateway) {
        this.foodTypeGateway = foodTypeGateway;
    }

    @Override
    public List<FoodTypeDto> execute() {
        return foodTypeGateway.getAllFoodTypes();
    }

}