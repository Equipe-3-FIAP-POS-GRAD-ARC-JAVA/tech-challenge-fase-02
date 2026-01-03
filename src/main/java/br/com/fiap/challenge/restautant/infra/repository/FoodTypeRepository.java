package br.com.fiap.challenge.restautant.infra.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.challenge.restautant.infra.entity.FoodType;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, UUID> {
}