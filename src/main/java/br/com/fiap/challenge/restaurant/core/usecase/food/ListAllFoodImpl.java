package br.com.fiap.challenge.restaurant.core.usecase.food;

import java.util.List;

import br.com.fiap.challenge.restaurant.core.dto.FoodDto;
import br.com.fiap.challenge.restaurant.core.gateway.FoodGateway;

public class ListAllFoodImpl implements ListAllFood {

    private final FoodGateway foodGateway;

    public ListAllFoodImpl(FoodGateway foodGateway) {
        this.foodGateway = foodGateway;
    }

    @Override
    public List<FoodDto> execute() {
        return foodGateway.getAllFoods();
    }
}