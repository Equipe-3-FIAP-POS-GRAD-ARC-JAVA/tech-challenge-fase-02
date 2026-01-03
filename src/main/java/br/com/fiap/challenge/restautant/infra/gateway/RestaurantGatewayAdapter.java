package br.com.fiap.challenge.restautant.infra.gateway;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;
import br.com.fiap.challenge.restautant.infra.entity.Address;
import br.com.fiap.challenge.restautant.infra.entity.Food;
import br.com.fiap.challenge.restautant.infra.entity.Menu;
import br.com.fiap.challenge.restautant.infra.entity.Restaurant;
import br.com.fiap.challenge.restautant.infra.entity.State;
import br.com.fiap.challenge.restautant.infra.entity.ZipCode;
import br.com.fiap.challenge.restautant.infra.repository.FoodTypeRepository;
import br.com.fiap.challenge.restautant.infra.repository.RestaurantRepository;

@Component
public class RestaurantGatewayAdapter implements RestaurantGateway {

    private final RestaurantRepository restaurantRepository;
    private final FoodTypeRepository foodTypeRepository;

    public RestaurantGatewayAdapter(RestaurantRepository restaurantRepository, FoodTypeRepository foodTypeRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodTypeRepository = foodTypeRepository;
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public RestaurantDto getRestaurantById(UUID restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantInput restaurantInput) {
        Address address = new Address(
                restaurantInput.address().street(),
                restaurantInput.address().number(),
                restaurantInput.address().city(),
                restaurantInput.address().neighborhood(),
                restaurantInput.address().country(),
                new State(restaurantInput.address().state()),
                new ZipCode(restaurantInput.address().zipCode())
        );
        Restaurant entity = new Restaurant(restaurantInput.name(), address);
        Restaurant saved = restaurantRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantInput restaurantInput) {
        Restaurant entity = restaurantRepository.findById(restaurantInput.id())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        entity.setName(restaurantInput.name());
        Address address = new Address(
                restaurantInput.address().street(),
                restaurantInput.address().number(),
                restaurantInput.address().city(),
                restaurantInput.address().neighborhood(),
                restaurantInput.address().country(),
                new State(restaurantInput.address().state()),
                new ZipCode(restaurantInput.address().zipCode())
        );
        entity.setAddress(address);
        Restaurant updated = restaurantRepository.save(entity);
        return toDto(updated);
    }

    @Override
    public void deleteRestaurant(UUID restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public List<String> getTypesFoods() {
        return foodTypeRepository.findAll().stream()
                .map(foodType -> foodType.getTypeFood())
                .toList();
    }

    private RestaurantDto toDto(Restaurant entity) {
        List<MenuDto> menuDtos = entity.getMenus() != null
            ? entity.getMenus().stream()
                .map(this::menuToDto)
                .toList()
            : List.of();
        
        return new RestaurantDto(entity.getId(), entity.getName(), menuDtos);
    }

    private MenuDto menuToDto(Menu menu) {
        List<FoodDto> foodDtos = menu.getFoods() != null
            ? menu.getFoods().stream()
                .map(this::foodToDto)
                .toList()
            : List.of();
        
        return new MenuDto(menu.getId(), menu.getRestaurant().getId(), foodDtos);
    }

    private FoodDto foodToDto(Food food) {
        return new FoodDto(
            food.getMenu().getId(),
            food.getId(),
            food.getName(),
            food.getDescription(),
            food.getFoodType().getId(),
            food.getPrice().doubleValue(),
            food.getImageUrl()
        );
    }
}