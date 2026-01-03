package br.com.fiap.challenge.restautant.infra.gateway;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;
import br.com.fiap.challenge.restautant.infra.entity.Address;
import br.com.fiap.challenge.restautant.infra.entity.Food;
import br.com.fiap.challenge.restautant.infra.entity.FoodType;
import br.com.fiap.challenge.restautant.infra.entity.Menu;
import br.com.fiap.challenge.restautant.infra.entity.Restaurant;
import br.com.fiap.challenge.restautant.infra.entity.State;
import br.com.fiap.challenge.restautant.infra.entity.ZipCode;
import br.com.fiap.challenge.restautant.infra.repository.FoodRepository;
import br.com.fiap.challenge.restautant.infra.repository.FoodTypeRepository;
import br.com.fiap.challenge.restautant.infra.repository.MenuRepository;

@ExtendWith(MockitoExtension.class)
class FoodGatewayAdapterTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private FoodTypeRepository foodTypeRepository;

    private FoodGatewayAdapter gateway;

    @BeforeEach
    void setUp() {
        gateway = new FoodGatewayAdapter(foodRepository, menuRepository, foodTypeRepository);
    }

    @Test
    @DisplayName("Should get all foods")
    void shouldGetAllFoods() {
        UUID foodId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();
        UUID foodTypeId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        Menu menu = new Menu(restaurant, null);
        menu.setId(menuId);
        FoodType foodType = new FoodType("Pizza");
        foodType.setId(foodTypeId);
        Food food = new Food("Pizza", "Delicious", foodType, BigDecimal.valueOf(10.0), "image.jpg", menu);
        food.setId(foodId);

        when(foodRepository.findAll()).thenReturn(List.of(food));

        List<FoodDto> result = gateway.getAllFoods();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).foodId()).isEqualTo(foodId);
        assertThat(result.get(0).name()).isEqualTo("Pizza");
        assertThat(result.get(0).description()).isEqualTo("Delicious");
        assertThat(result.get(0).foodTypeId()).isEqualTo(foodTypeId);
        assertThat(result.get(0).price()).isEqualTo(10.0);
        assertThat(result.get(0).imageURL()).isEqualTo("image.jpg");
        assertThat(result.get(0).menuId()).isEqualTo(menuId);
    }

    @Test
    @DisplayName("Should get food by id")
    void shouldGetFoodById() {
        UUID foodId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();
        UUID foodTypeId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        Menu menu = new Menu(restaurant, null);
        menu.setId(menuId);
        FoodType foodType = new FoodType("Pizza");
        foodType.setId(foodTypeId);
        Food food = new Food("Pizza", "Delicious", foodType, BigDecimal.valueOf(10.0), "image.jpg", menu);
        food.setId(foodId);

        when(foodRepository.findById(foodId)).thenReturn(Optional.of(food));

        FoodDto result = gateway.getFoodById(foodId);

        assertThat(result).isNotNull();
        assertThat(result.foodId()).isEqualTo(foodId);
        assertThat(result.name()).isEqualTo("Pizza");
    }

    @Test
    @DisplayName("Should return null when food not found")
    void shouldReturnNullWhenFoodNotFound() {
        UUID foodId = UUID.randomUUID();

        when(foodRepository.findById(foodId)).thenReturn(Optional.empty());

        FoodDto result = gateway.getFoodById(foodId);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Should get foods by menu id")
    void shouldGetFoodsByMenuId() {
        UUID foodId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();
        UUID foodTypeId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        Menu menu = new Menu(restaurant, null);
        menu.setId(menuId);
        FoodType foodType = new FoodType("Pizza");
        foodType.setId(foodTypeId);
        Food food = new Food("Pizza", "Delicious", foodType, BigDecimal.valueOf(10.0), "image.jpg", menu);
        food.setId(foodId);

        when(foodRepository.findByMenuId(menuId)).thenReturn(List.of(food));

        List<FoodDto> result = gateway.getFoodsByMenuId(menuId);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).foodId()).isEqualTo(foodId);
    }

    @Test
    @DisplayName("Should create food")
    void shouldCreateFood() {
        UUID foodId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();
        UUID foodTypeId = UUID.randomUUID();
        FoodInput input = new FoodInput(null, menuId, "Pizza", "Delicious", foodTypeId, 10.0, "image.jpg");
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        Menu menu = new Menu(restaurant, null);
        menu.setId(menuId);
        FoodType foodType = new FoodType("Pizza");
        foodType.setId(foodTypeId);
        Food food = new Food("Pizza", "Delicious", foodType, BigDecimal.valueOf(10.0), "image.jpg", menu);
        food.setId(foodId);

        when(menuRepository.findById(menuId)).thenReturn(Optional.of(menu));
        when(foodTypeRepository.findById(foodTypeId)).thenReturn(Optional.of(foodType));
        when(foodRepository.save(any(Food.class))).thenReturn(food);

        FoodDto result = gateway.createFood(input);

        assertThat(result).isNotNull();
        assertThat(result.foodId()).isEqualTo(foodId);
        assertThat(result.name()).isEqualTo("Pizza");
    }

    @Test
    @DisplayName("Should update food")
    void shouldUpdateFood() {
        UUID foodId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();
        UUID foodTypeId = UUID.randomUUID();
        FoodInput input = new FoodInput(foodId, menuId, "Updated Pizza", "Updated", foodTypeId, 15.0, "updated.jpg");
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        Menu menu = new Menu(restaurant, null);
        menu.setId(menuId);
        FoodType foodType = new FoodType("Pizza");
        foodType.setId(foodTypeId);
        Food existingFood = new Food("Pizza", "Delicious", foodType, BigDecimal.valueOf(10.0), "image.jpg", menu);
        existingFood.setId(foodId);
        Food updatedFood = new Food("Updated Pizza", "Updated", foodType, BigDecimal.valueOf(15.0), "updated.jpg", menu);
        updatedFood.setId(foodId);

        when(foodRepository.findById(foodId)).thenReturn(Optional.of(existingFood));
        when(menuRepository.findById(menuId)).thenReturn(Optional.of(menu));
        when(foodTypeRepository.findById(foodTypeId)).thenReturn(Optional.of(foodType));
        when(foodRepository.save(any(Food.class))).thenReturn(updatedFood);

        FoodDto result = gateway.updateFood(input);

        assertThat(result).isNotNull();
        assertThat(result.foodId()).isEqualTo(foodId);
        assertThat(result.name()).isEqualTo("Updated Pizza");
        assertThat(result.price()).isEqualTo(15.0);
    }

    @Test
    @DisplayName("Should delete food")
    void shouldDeleteFood() {
        UUID foodId = UUID.randomUUID();

        gateway.deleteFood(foodId);

        verify(foodRepository).deleteById(foodId);
    }
}