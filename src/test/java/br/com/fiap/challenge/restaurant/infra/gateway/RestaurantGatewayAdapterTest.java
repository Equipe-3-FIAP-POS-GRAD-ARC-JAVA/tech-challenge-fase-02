package br.com.fiap.challenge.restaurant.infra.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.AddressInput;
import br.com.fiap.challenge.restaurant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restaurant.infra.entity.Address;
import br.com.fiap.challenge.restaurant.infra.entity.FoodType;
import br.com.fiap.challenge.restaurant.infra.entity.Restaurant;
import br.com.fiap.challenge.restaurant.infra.entity.State;
import br.com.fiap.challenge.restaurant.infra.entity.ZipCode;
import br.com.fiap.challenge.restaurant.infra.repository.FoodTypeRepository;
import br.com.fiap.challenge.restaurant.infra.repository.RestaurantRepository;

@ExtendWith(MockitoExtension.class)
class RestaurantGatewayAdapterTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private FoodTypeRepository foodTypeRepository;

    private RestaurantGatewayAdapter gateway;

    @BeforeEach
    void setUp() {
        gateway = new RestaurantGatewayAdapter(restaurantRepository, foodTypeRepository);
    }

    @Test
    void shouldGetAllRestaurants() {
        UUID restaurantId = UUID.randomUUID();
        Address address = new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345"));
        Restaurant restaurant = new Restaurant("Test Restaurant", address);
        restaurant.setId(restaurantId);

        when(restaurantRepository.findAll()).thenReturn(List.of(restaurant));

        List<RestaurantDto> result = gateway.getAllRestaurants();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).restaurantId()).isEqualTo(restaurantId);
        assertThat(result.get(0).name()).isEqualTo("Test Restaurant");
    }

    @Test
    void shouldGetRestaurantById() {
        UUID restaurantId = UUID.randomUUID();
        Address address = new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345"));
        Restaurant restaurant = new Restaurant("Test Restaurant", address);
        restaurant.setId(restaurantId);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        RestaurantDto result = gateway.getRestaurantById(restaurantId);

        assertThat(result).isNotNull();
        assertThat(result.restaurantId()).isEqualTo(restaurantId);
        assertThat(result.name()).isEqualTo("Test Restaurant");
    }

    @Test
    void shouldReturnNullWhenRestaurantNotFound() {
        UUID restaurantId = UUID.randomUUID();

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        RestaurantDto result = gateway.getRestaurantById(restaurantId);

        assertThat(result).isNull();
    }

    @Test
    void shouldCreateRestaurant() {
        AddressInput addressInput = new AddressInput("Street", "123", "City", "Neighborhood", "Country", "State", "12345");
        RestaurantInput input = new RestaurantInput(null, "New Restaurant", addressInput);
        UUID generatedId = UUID.randomUUID();
        Address address = new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345"));
        Restaurant savedRestaurant = new Restaurant("New Restaurant", address);
        savedRestaurant.setId(generatedId);

        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(savedRestaurant);

        RestaurantDto result = gateway.createRestaurant(input);

        assertThat(result.restaurantId()).isEqualTo(generatedId);
        assertThat(result.name()).isEqualTo("New Restaurant");
    }

    @Test
    void shouldUpdateRestaurant() {
        UUID restaurantId = UUID.randomUUID();
        AddressInput addressInput = new AddressInput("Updated Street", "456", "Updated City", "Neighborhood", "Country", "State", "67890");
        RestaurantInput input = new RestaurantInput(restaurantId, "Updated Restaurant", addressInput);
        Address oldAddress = new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345"));
        Restaurant existingRestaurant = new Restaurant("Old Name", oldAddress);
        existingRestaurant.setId(restaurantId);
        Address updatedAddress = new Address("Updated Street", "456", "Updated City", "Neighborhood", "Country", new State("State"), new ZipCode("67890"));
        Restaurant updatedRestaurant = new Restaurant("Updated Restaurant", updatedAddress);
        updatedRestaurant.setId(restaurantId);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(existingRestaurant));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(updatedRestaurant);

        RestaurantDto result = gateway.updateRestaurant(input);

        assertThat(result.restaurantId()).isEqualTo(restaurantId);
        assertThat(result.name()).isEqualTo("Updated Restaurant");
    }

    @Test
    void shouldDeleteRestaurant() {
        UUID restaurantId = UUID.randomUUID();

        gateway.deleteRestaurant(restaurantId);

        verify(restaurantRepository).deleteById(restaurantId);
    }

    @Test
    void shouldGetTypesFoods() {
        FoodType type1 = new FoodType("Italian");
        type1.setId(UUID.randomUUID());
        FoodType type2 = new FoodType("Brazilian");
        type2.setId(UUID.randomUUID());

        when(foodTypeRepository.findAll()).thenReturn(List.of(type1, type2));

        List<String> result = gateway.getTypesFoods();

        assertThat(result).hasSize(2);
        assertThat(result).contains("Italian", "Brazilian");
        verify(foodTypeRepository).findAll();
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentRestaurant() {
        UUID restaurantId = UUID.randomUUID();
        AddressInput addressInput = new AddressInput("Street", "123", "City", "Neighborhood", "Country", "State", "12345");
        RestaurantInput input = new RestaurantInput(restaurantId, "Restaurant", addressInput);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        try {
            gateway.updateRestaurant(input);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Restaurant not found");
        }
    }

    @Test
    void shouldHandleRestaurantWithMenusAndFoods() {
        UUID restaurantId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();
        UUID foodId = UUID.randomUUID();
        UUID foodTypeId = UUID.randomUUID();
        
        Address address = new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345"));
        Restaurant restaurant = new Restaurant("Test Restaurant", address);
        restaurant.setId(restaurantId);
        
        FoodType foodType = new FoodType("Italian");
        foodType.setId(foodTypeId);
        
        br.com.fiap.challenge.restaurant.infra.entity.Menu menu = new br.com.fiap.challenge.restaurant.infra.entity.Menu(restaurant, null);
        menu.setId(menuId);
        
        br.com.fiap.challenge.restaurant.infra.entity.Food food = new br.com.fiap.challenge.restaurant.infra.entity.Food(
            "Pizza",
            "Delicious pizza",
            foodType,
            new java.math.BigDecimal("25.50"),
            "pizza.jpg",
            menu
        );
        food.setId(foodId);
        
        menu.setFoods(List.of(food));
        restaurant.setMenus(List.of(menu));

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        RestaurantDto result = gateway.getRestaurantById(restaurantId);

        assertThat(result).isNotNull();
        assertThat(result.restaurantId()).isEqualTo(restaurantId);
        assertThat(result.menus()).hasSize(1);
        assertThat(result.menus().get(0).menuId()).isEqualTo(menuId);
        assertThat(result.menus().get(0).foods()).hasSize(1);
        assertThat(result.menus().get(0).foods().get(0).foodId()).isEqualTo(foodId);
    }
}