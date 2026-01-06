package br.com.fiap.challenge.restaurant.infra.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.challenge.restaurant.infra.entity.OpeningHours;

public interface OpeningHoursRepository extends JpaRepository<OpeningHours, UUID> {

    List<OpeningHours> findByRestaurantId(UUID restaurantId);

    Optional<OpeningHours> findByRestaurantIdAndWeekday(UUID restaurantId, String weekday);
}
