package br.com.fiap.challenge.restaurant.infra.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.challenge.restaurant.infra.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmailIgnoreCase(String email);
    boolean existsByLoginIgnoreCase(String login);

}
