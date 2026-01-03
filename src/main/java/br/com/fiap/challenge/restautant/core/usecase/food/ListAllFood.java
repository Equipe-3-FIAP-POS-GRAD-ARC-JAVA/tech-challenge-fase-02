package br.com.fiap.challenge.restautant.core.usecase.food;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;

import java.util.List;

public interface ListAllFood {

    List<FoodDto> execute();
}