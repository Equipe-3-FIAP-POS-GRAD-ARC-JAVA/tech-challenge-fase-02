package br.com.fiap.challenge.restautant.infra.repository;

import br.com.fiap.challenge.restautant.infra.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, UUID> {
}