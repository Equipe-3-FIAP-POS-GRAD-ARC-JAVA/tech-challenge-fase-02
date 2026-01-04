package br.com.fiap.challenge.restaurant.infra.gateway;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursInput;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursUpdateInput;
import br.com.fiap.challenge.restaurant.core.gateway.OpeningHoursGateway;
import br.com.fiap.challenge.restaurant.infra.entity.OpeningHours;
import br.com.fiap.challenge.restaurant.infra.entity.Restaurant;
import br.com.fiap.challenge.restaurant.infra.repository.OpeningHoursRepository;
import br.com.fiap.challenge.restaurant.infra.repository.RestaurantRepository;

@Component
public class OpeningHoursGatewayAdapter implements OpeningHoursGateway {

    private final OpeningHoursRepository openingHoursRepository;
    private final RestaurantRepository restaurantRepository;

    public OpeningHoursGatewayAdapter(OpeningHoursRepository openingHoursRepository,
                                      RestaurantRepository restaurantRepository) {
        this.openingHoursRepository = openingHoursRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public OpeningHoursDto createOpeningHours(OpeningHoursInput input) {
        Restaurant restaurant = restaurantRepository.findById(input.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        OpeningHours entity = new OpeningHours(
                restaurant,
                input.weekday(),
                input.opensAt(),
                input.closesAt(),
                input.isClosed()
        );

        OpeningHours saved = openingHoursRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public OpeningHoursDto updateOpeningHours(OpeningHoursUpdateInput input) {
        OpeningHours entity = openingHoursRepository.findById(input.id())
                .orElseThrow(() -> new RuntimeException("OpeningHours not found"));

        Restaurant restaurant = restaurantRepository.findById(input.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        entity.setRestaurant(restaurant);
        entity.setWeekday(input.weekda());
        entity.setOpensAt(input.opensAt());
        entity.setClosesAt(input.closesAt());
        entity.setClosed(input.isClosed());

        OpeningHours updated = openingHoursRepository.save(entity);
        return toDto(updated);
    }

    @Override
    public List<OpeningHoursDto> listOpeningHoursByRestaurant(UUID restaurantId) {
        return openingHoursRepository.findByRestaurantId(restaurantId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private OpeningHoursDto toDto(OpeningHours entity) {
        return new OpeningHoursDto(
                entity.getId(),
                entity.getRestaurant().getId(),
                entity.getWeekday(),
                entity.getOpensAt(),
                entity.getClosesAt(),
                entity.isClosed()
        );
    }
}
