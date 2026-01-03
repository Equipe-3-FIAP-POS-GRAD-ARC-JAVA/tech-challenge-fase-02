package br.com.fiap.challenge.restautant.infra.gateway;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.dto.AddressInput;
import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restautant.infra.entity.Address;
import br.com.fiap.challenge.restautant.infra.entity.Restaurant;
import br.com.fiap.challenge.restautant.infra.entity.State;
import br.com.fiap.challenge.restautant.infra.entity.ZipCode;
import br.com.fiap.challenge.restautant.infra.repository.RestaurantRepository;

@ExtendWith(MockitoExtension.class)
class RestaurantGatewayAdapterTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    private RestaurantGatewayAdapter gateway;

    @BeforeEach
    void setUp() {
        gateway = new RestaurantGatewayAdapter(restaurantRepository);
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
}