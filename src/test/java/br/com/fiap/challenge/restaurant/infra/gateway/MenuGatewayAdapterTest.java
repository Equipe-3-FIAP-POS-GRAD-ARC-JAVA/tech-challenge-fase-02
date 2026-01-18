package br.com.fiap.challenge.restaurant.infra.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.dto.MenuInput;
import br.com.fiap.challenge.restaurant.infra.entity.Address;
import br.com.fiap.challenge.restaurant.infra.entity.Menu;
import br.com.fiap.challenge.restaurant.infra.entity.Restaurant;
import br.com.fiap.challenge.restaurant.infra.entity.State;
import br.com.fiap.challenge.restaurant.infra.entity.ZipCode;
import br.com.fiap.challenge.restaurant.infra.repository.MenuRepository;
import br.com.fiap.challenge.restaurant.infra.repository.RestaurantRepository;

@ExtendWith(MockitoExtension.class)
class MenuGatewayAdapterTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    private MenuGatewayAdapter gateway;

    @BeforeEach
    void setUp() {
        gateway = new MenuGatewayAdapter(menuRepository, restaurantRepository);
    }

    @Test
    @DisplayName("Should get all menus")
    void shouldGetAllMenus() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        restaurant.setId(restaurantId);
        Menu menu = new Menu(restaurant, null);
        menu.setId(menuId);

        when(menuRepository.findAll()).thenReturn(List.of(menu));

        List<MenuDto> result = gateway.getAllMenus();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).menuId()).isEqualTo(menuId);
        assertThat(result.get(0).restaurantId()).isEqualTo(restaurantId);
    }

    @Test
    @DisplayName("Should get menu by id")
    void shouldGetMenuById() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        restaurant.setId(restaurantId);
        Menu menu = new Menu(restaurant, null);
        menu.setId(menuId);

        when(menuRepository.findById(menuId)).thenReturn(Optional.of(menu));

        MenuDto result = gateway.getMenuById(menuId);

        assertThat(result).isNotNull();
        assertThat(result.menuId()).isEqualTo(menuId);
        assertThat(result.restaurantId()).isEqualTo(restaurantId);
    }

    @Test
    @DisplayName("Should return null when menu not found")
    void shouldReturnNullWhenMenuNotFound() {
        UUID menuId = UUID.randomUUID();

        when(menuRepository.findById(menuId)).thenReturn(Optional.empty());

        MenuDto result = gateway.getMenuById(menuId);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Should get menus by restaurant id")
    void shouldGetMenusByRestaurantId() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        restaurant.setId(restaurantId);
        Menu menu = new Menu(restaurant, null);
        menu.setId(menuId);

        when(menuRepository.findByRestaurantId(restaurantId)).thenReturn(List.of(menu));

        List<MenuDto> result = gateway.getMenusByRestaurantId(restaurantId);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).menuId()).isEqualTo(menuId);
    }

    @Test
    @DisplayName("Should create menu")
    void shouldCreateMenu() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        MenuInput input = new MenuInput(null, restaurantId);
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        restaurant.setId(restaurantId);
        Menu menu = new Menu(restaurant, null);
        menu.setId(menuId);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(menuRepository.save(any(Menu.class))).thenReturn(menu);

        MenuDto result = gateway.createMenu(input);

        assertThat(result).isNotNull();
        assertThat(result.menuId()).isEqualTo(menuId);
        assertThat(result.restaurantId()).isEqualTo(restaurantId);
    }

    @Test
    @DisplayName("Should update menu")
    void shouldUpdateMenu() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        MenuInput input = new MenuInput(menuId, restaurantId);
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        restaurant.setId(restaurantId);
        Menu existingMenu = new Menu(restaurant, null);
        existingMenu.setId(menuId);
        Menu updatedMenu = new Menu(restaurant, null);
        updatedMenu.setId(menuId);

        when(menuRepository.findById(menuId)).thenReturn(Optional.of(existingMenu));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(menuRepository.save(any(Menu.class))).thenReturn(updatedMenu);

        MenuDto result = gateway.updateMenu(input);

        assertThat(result).isNotNull();
        assertThat(result.menuId()).isEqualTo(menuId);
        assertThat(result.restaurantId()).isEqualTo(restaurantId);
    }

    @Test
    @DisplayName("Should delete menu")
    void shouldDeleteMenu() {
        UUID menuId = UUID.randomUUID();

        gateway.deleteMenu(menuId);

        verify(menuRepository).deleteById(menuId);
    }

    @Test
    @DisplayName("Should throw exception when creating menu with non-existent restaurant")
    void shouldThrowExceptionWhenCreatingMenuWithNonExistentRestaurant() {
        UUID restaurantId = UUID.randomUUID();
        MenuInput input = new MenuInput(null, restaurantId);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        try {
            gateway.createMenu(input);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Restaurant not found");
        }
    }

    @Test
    @DisplayName("Should throw exception when updating menu with non-existent restaurant")
    void shouldThrowExceptionWhenUpdatingMenuWithNonExistentRestaurant() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        MenuInput input = new MenuInput(menuId, restaurantId);
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        restaurant.setId(restaurantId);
        Menu existingMenu = new Menu(restaurant, null);
        existingMenu.setId(menuId);

        when(menuRepository.findById(menuId)).thenReturn(Optional.of(existingMenu));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        try {
            gateway.updateMenu(input);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Restaurant not found");
        }
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent menu")
    void shouldThrowExceptionWhenUpdatingNonExistentMenu() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        MenuInput input = new MenuInput(menuId, restaurantId);

        when(menuRepository.findById(menuId)).thenReturn(Optional.empty());

        try {
            gateway.updateMenu(input);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Menu not found");
        }
    }

    @Test
    @DisplayName("Should handle menu with foods")
    void shouldHandleMenuWithFoods() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        UUID foodId = UUID.randomUUID();
        UUID foodTypeId = UUID.randomUUID();
        
        Restaurant restaurant = new Restaurant("Test Restaurant", new Address("Street", "123", "City", "Neighborhood", "Country", new State("State"), new ZipCode("12345")));
        restaurant.setId(restaurantId);
        
        br.com.fiap.challenge.restaurant.infra.entity.FoodType foodType = new br.com.fiap.challenge.restaurant.infra.entity.FoodType("Italian");
        foodType.setId(foodTypeId);
        
        Menu menu = new Menu(restaurant, null);
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

        when(menuRepository.findById(menuId)).thenReturn(Optional.of(menu));

        MenuDto result = gateway.getMenuById(menuId);

        assertThat(result).isNotNull();
        assertThat(result.menuId()).isEqualTo(menuId);
        assertThat(result.foods()).hasSize(1);
        assertThat(result.foods().get(0).foodId()).isEqualTo(foodId);
        assertThat(result.foods().get(0).name()).isEqualTo("Pizza");
    }
}