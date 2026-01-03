package br.com.fiap.challenge.restautant.infra.repository;

import br.com.fiap.challenge.restautant.infra.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {

    List<Food> findByMenuId(UUID menuId);
}