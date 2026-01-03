package br.com.fiap.challenge.restautant.infra.gateway;

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restautant.core.gateway.FoodTypeGateway;
import br.com.fiap.challenge.restautant.infra.entity.FoodType;
import br.com.fiap.challenge.restautant.infra.repository.FoodTypeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FoodTypeGatewayAdapter implements FoodTypeGateway {

    private final FoodTypeRepository foodTypeRepository;

    public FoodTypeGatewayAdapter(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    @Override
    public List<FoodTypeDto> getAllFoodTypes() {
        return foodTypeRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FoodTypeDto getFoodTypeById(UUID foodTypeId) {
        return foodTypeRepository.findById(foodTypeId)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public FoodTypeDto createFoodType(FoodTypeInput foodTypeInput) {
        FoodType entity = new FoodType(foodTypeInput.name());
        FoodType saved = foodTypeRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public FoodTypeDto updateFoodType(FoodTypeInput foodTypeInput) {
        FoodType entity = foodTypeRepository.findById(foodTypeInput.id())
                .orElseThrow(() -> new RuntimeException("FoodType not found"));
        entity.setTypeFood(foodTypeInput.name());
        FoodType updated = foodTypeRepository.save(entity);
        return toDto(updated);
    }

    @Override
    public void deleteFoodType(UUID foodTypeId) {
        foodTypeRepository.deleteById(foodTypeId);
    }

    private FoodTypeDto toDto(FoodType entity) {
        return new FoodTypeDto(entity.getId(), entity.getTypeFood());
    }
}