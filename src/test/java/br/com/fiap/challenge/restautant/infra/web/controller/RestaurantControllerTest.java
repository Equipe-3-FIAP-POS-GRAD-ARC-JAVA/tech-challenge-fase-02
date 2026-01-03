package br.com.fiap.challenge.restautant.infra.web.controller;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.challenge.restautant.core.dto.AddressInput;
import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.CreateRestaurant;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.DeleteRestaurant;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.ListAllRestaurant;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.ListRestaurantById;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.UpdateRestaurant;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    @Mock
    private CreateRestaurant createRestaurant;

    @Mock
    private ListAllRestaurant listAllRestaurant;

    @Mock
    private ListRestaurantById listRestaurantById;

    @Mock
    private UpdateRestaurant updateRestaurant;

    @Mock
    private DeleteRestaurant deleteRestaurant;

    @InjectMocks
    private RestaurantController controller;

    @Test
    @DisplayName("Should create restaurant")
    void shouldCreateRestaurant() {
        UUID restaurantId = UUID.randomUUID();
        AddressInput address = new AddressInput("Street", "123", "City", "Neighborhood", "Country", "State", "12345");
        RestaurantInput input = new RestaurantInput(null, "Restaurant Name", address);
        RestaurantDto expected = new RestaurantDto(restaurantId, "Restaurant Name", null);

        when(createRestaurant.execute(any(RestaurantInput.class))).thenReturn(expected);

        ResponseEntity<RestaurantDto> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should list all restaurants")
    void shouldListAllRestaurants() {
        UUID restaurantId = UUID.randomUUID();
        RestaurantDto restaurant = new RestaurantDto(restaurantId, "Restaurant Name", null);
        List<RestaurantDto> restaurants = List.of(restaurant);

        when(listAllRestaurant.execute()).thenReturn(restaurants);

        ResponseEntity<List<RestaurantDto>> response = controller.listAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).restaurantId()).isEqualTo(restaurantId);
    }

    @Test
    @DisplayName("Should get restaurant by id")
    void shouldGetRestaurantById() {
        UUID restaurantId = UUID.randomUUID();
        RestaurantDto restaurant = new RestaurantDto(restaurantId, "Restaurant Name", null);

        when(listRestaurantById.execute(restaurantId)).thenReturn(restaurant);

        ResponseEntity<RestaurantDto> response = controller.getById(restaurantId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(restaurant);
    }

    @Test
    @DisplayName("Should return not found when restaurant not exists")
    void shouldReturnNotFoundWhenRestaurantNotExists() {
        UUID restaurantId = UUID.randomUUID();

        when(listRestaurantById.execute(restaurantId)).thenReturn(null);

        ResponseEntity<RestaurantDto> response = controller.getById(restaurantId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Should update restaurant")
    void shouldUpdateRestaurant() {
        UUID restaurantId = UUID.randomUUID();
        AddressInput address = new AddressInput("Updated Street", "456", "Updated City", "Neighborhood", "Country", "State", "67890");
        RestaurantInput input = new RestaurantInput(restaurantId, "Updated Name", address);
        RestaurantDto expected = new RestaurantDto(restaurantId, "Updated Name", null);

        when(updateRestaurant.execute(any(RestaurantInput.class))).thenReturn(expected);

        ResponseEntity<RestaurantDto> response = controller.update(restaurantId, input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().name()).isEqualTo("Updated Name");
    }

    @Test
    @DisplayName("Should delete restaurant")
    void shouldDeleteRestaurant() {
        UUID restaurantId = UUID.randomUUID();

        doNothing().when(deleteRestaurant).execute(restaurantId);

        ResponseEntity<Void> response = controller.delete(restaurantId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        verify(deleteRestaurant).execute(restaurantId);
    }
}