package br.com.fiap.challenge.restautant.infra.gateway;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;
import br.com.fiap.challenge.restautant.core.gateway.FoodGateway;
import br.com.fiap.challenge.restautant.infra.entity.Food;
import br.com.fiap.challenge.restautant.infra.entity.FoodType;
import br.com.fiap.challenge.restautant.infra.entity.Menu;
import br.com.fiap.challenge.restautant.infra.repository.FoodRepository;
import br.com.fiap.challenge.restautant.infra.repository.FoodTypeRepository;
import br.com.fiap.challenge.restautant.infra.repository.MenuRepository;

@Component
public class FoodGatewayAdapter implements FoodGateway {

    private final FoodRepository foodRepository;
    private final MenuRepository menuRepository;
    private final FoodTypeRepository foodTypeRepository;

    public FoodGatewayAdapter(FoodRepository foodRepository, MenuRepository menuRepository, FoodTypeRepository foodTypeRepository) {
        this.foodRepository = foodRepository;
        this.menuRepository = menuRepository;
        this.foodTypeRepository = foodTypeRepository;
    }

    @Override
    public List<FoodDto> getAllFoods() {
        return foodRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FoodDto getFoodById(UUID foodId) {
        return foodRepository.findById(foodId)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public List<FoodDto> getFoodsByMenuId(UUID menuId) {
        return foodRepository.findByMenuId(menuId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FoodDto createFood(FoodInput foodInput) {
        Menu menu = menuRepository.findById(foodInput.menuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        FoodType foodType = foodTypeRepository.findById(foodInput.foodTypeId())
                .orElseThrow(() -> new RuntimeException("FoodType not found"));
        Food entity = new Food(foodInput.name(), foodInput.description(), foodType,
                BigDecimal.valueOf(foodInput.price()), foodInput.imageURL(), menu);
        Food saved = foodRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public FoodDto updateFood(FoodInput foodInput) {
        Food entity = foodRepository.findById(foodInput.id())
                .orElseThrow(() -> new RuntimeException("Food not found"));
        entity.setName(foodInput.name());
        entity.setDescription(foodInput.description());
        FoodType foodType = foodTypeRepository.findById(foodInput.foodTypeId())
                .orElseThrow(() -> new RuntimeException("FoodType not found"));
        entity.setFoodType(foodType);
        entity.setPrice(BigDecimal.valueOf(foodInput.price()));
        entity.setImageUrl(foodInput.imageURL());
        Menu menu = menuRepository.findById(foodInput.menuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        entity.setMenu(menu);
        Food updated = foodRepository.save(entity);
        return toDto(updated);
    }

    @Override
    public void deleteFood(UUID foodId) {
        foodRepository.deleteById(foodId);
    }

    private FoodDto toDto(Food entity) {
        return new FoodDto(entity.getMenu().getId(), entity.getId(), entity.getName(), entity.getDescription(),
                entity.getFoodType().getId(), entity.getPrice().doubleValue(), entity.getImageUrl());
    }
}