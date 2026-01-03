package br.com.fiap.challenge.restautant.infra.repository;

import br.com.fiap.challenge.restautant.infra.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, UUID> {
}