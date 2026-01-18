package br.com.fiap.challenge.restaurant.infra.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.challenge.restaurant.infra.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {

    List<Food> findByMenuId(UUID menuId);
}