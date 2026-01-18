package br.com.fiap.challenge.restaurant.infra.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.challenge.restaurant.infra.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
}