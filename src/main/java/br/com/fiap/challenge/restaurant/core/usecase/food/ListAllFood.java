package br.com.fiap.challenge.restaurant.core.usecase.food;

import br.com.fiap.challenge.restaurant.core.dto.FoodDto;

import java.util.List;

public interface ListAllFood {

    List<FoodDto> execute();
}