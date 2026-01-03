package br.com.fiap.challenge.restautant.infra.gateway;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;
import br.com.fiap.challenge.restautant.infra.entity.Address;
import br.com.fiap.challenge.restautant.infra.entity.Restaurant;
import br.com.fiap.challenge.restautant.infra.entity.State;
import br.com.fiap.challenge.restautant.infra.entity.ZipCode;
import br.com.fiap.challenge.restautant.infra.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantGatewayAdapter implements RestaurantGateway {

    private final RestaurantRepository restaurantRepository;

    public RestaurantGatewayAdapter(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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
        // TODO: implement if needed
        return List.of();
    }

    private RestaurantDto toDto(Restaurant entity) {
        // Note: RestaurantDto expects MenuDto, but entity has no menu. Adjust as needed.
        return new RestaurantDto(entity.getId(), entity.getName(), null);
    }
}