package br.com.fiap.challenge.restaurant.infra.gateway;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantInput;
import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;
import br.com.fiap.challenge.restaurant.infra.entity.OwnerRestaurant;
import br.com.fiap.challenge.restaurant.infra.entity.Restaurant;
import br.com.fiap.challenge.restaurant.infra.entity.User;
import br.com.fiap.challenge.restaurant.infra.repository.OwnerRestaurantRepository;
import br.com.fiap.challenge.restaurant.infra.repository.RestaurantRepository;
import br.com.fiap.challenge.restaurant.infra.repository.UserRepository;

@Component
public class OwnerRestaurantGatewayAdapter implements OwnerRestaurantGateway {

    private final OwnerRestaurantRepository ownerRestaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public OwnerRestaurantGatewayAdapter(
            OwnerRestaurantRepository ownerRestaurantRepository,
            UserRepository userRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.ownerRestaurantRepository = ownerRestaurantRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public OwnerRestaurantDto link(OwnerRestaurantInput input) {
        if (exists(input.ownerId(), input.restaurantId())) {
            throw new IllegalArgumentException("Owner already linked to restaurant");
        }

        User owner = userRepository.findById(input.ownerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (owner.getRole() != Role.OWNER_RESTAURANT) {
            throw new IllegalArgumentException("User is not OWNER_RESTAURANT");
        }

        Restaurant restaurant = restaurantRepository.findById(input.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        OwnerRestaurant saved = ownerRestaurantRepository.save(new OwnerRestaurant(owner, restaurant));
        return toDto(saved);
    }

    @Override
    public void unlink(UUID ownerId, UUID restaurantId) {
        OwnerRestaurant rel = ownerRestaurantRepository
                .findByOwner_IdAndRestaurant_Id(ownerId, restaurantId)
                .orElseThrow(() -> new RuntimeException("Relation not found"));

        ownerRestaurantRepository.delete(rel);
    }

    @Override
    public List<OwnerRestaurantDto> listByOwner(UUID ownerId) {
        return ownerRestaurantRepository.findAllByOwner_Id(ownerId).stream().map(this::toDto).toList();
    }

    @Override
    public List<OwnerRestaurantDto> listByRestaurant(UUID restaurantId) {
        return ownerRestaurantRepository.findAllByRestaurant_Id(restaurantId).stream().map(this::toDto).toList();
    }

    @Override
    public boolean exists(UUID ownerId, UUID restaurantId) {
        return ownerRestaurantRepository.existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId);
    }

    private OwnerRestaurantDto toDto(OwnerRestaurant entity) {
        return new OwnerRestaurantDto(
                entity.getId(),
                entity.getOwner().getId(),
                entity.getRestaurant().getId()
        );
    }
}
