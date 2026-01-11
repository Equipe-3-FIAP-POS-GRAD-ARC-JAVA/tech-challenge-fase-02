package br.com.fiap.challenge.restaurant.infra.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.challenge.restaurant.infra.entity.OwnerRestaurant;

public interface OwnerRestaurantRepository extends JpaRepository<OwnerRestaurant, UUID> {

    boolean existsByOwner_IdAndRestaurant_Id(UUID ownerId, UUID restaurantId);

    Optional<OwnerRestaurant> findByOwner_IdAndRestaurant_Id(UUID ownerId, UUID restaurantId);

    List<OwnerRestaurant> findAllByOwner_Id(UUID ownerId);

    List<OwnerRestaurant> findAllByRestaurant_Id(UUID restaurantId);
}
