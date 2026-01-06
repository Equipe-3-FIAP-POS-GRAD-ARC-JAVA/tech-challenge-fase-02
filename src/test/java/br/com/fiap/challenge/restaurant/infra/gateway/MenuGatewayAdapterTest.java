package br.com.fiap.challenge.restaurant.infra.gateway;

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
}