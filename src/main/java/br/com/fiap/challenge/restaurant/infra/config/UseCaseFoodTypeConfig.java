package br.com.fiap.challenge.restaurant.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.challenge.restaurant.core.gateway.FoodTypeGateway;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.CreateFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.CreateFoodTypeImpl;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.DeleteFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.DeleteFoodTypeImpl;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.ListAllFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.ListAllFoodTypeImpl;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.ListFoodTypeById;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.ListFoodTypeByIdImpl;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.UpdateFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.UpdateFoodTypeImpl;

@Configuration
public class UseCaseFoodTypeConfig {

    @Bean
    public CreateFoodType createFoodType(FoodTypeGateway foodTypeGateway) {
        return new CreateFoodTypeImpl(foodTypeGateway);
    }

    @Bean
    public DeleteFoodType deleteFoodType(FoodTypeGateway foodTypeGateway) {
        return new DeleteFoodTypeImpl(foodTypeGateway);
    }

    @Bean
    public ListAllFoodType listAllFoodType(FoodTypeGateway foodTypeGateway) {
        return new ListAllFoodTypeImpl(foodTypeGateway);
    }

    @Bean
    public ListFoodTypeById listFoodTypeById(FoodTypeGateway foodTypeGateway) {
        return new ListFoodTypeByIdImpl(foodTypeGateway);
    }

    @Bean
    public UpdateFoodType updateFoodType(FoodTypeGateway foodTypeGateway) {
        return new UpdateFoodTypeImpl(foodTypeGateway);
    }
}