package br.com.fiap.challenge.restaurant.infra.gateway;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.dto.UserInput;
import br.com.fiap.challenge.restaurant.core.gateway.UserGateway;
import br.com.fiap.challenge.restaurant.infra.entity.User;
import br.com.fiap.challenge.restaurant.infra.repository.UserRepository;

@Component
public class UserGatewayAdapter implements UserGateway {

    private final UserRepository userRepository;

    public UserGatewayAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public UserDto createUser(UserInput userInput) {
        User entity = new User(
                userInput.name(),
                userInput.email(),
                userInput.login(),
                userInput.password(),
                userInput.active(),
                userInput.role()
        );

        User saved = userRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public UserDto updateUser(UserInput userInput) {
        User entity = userRepository.findById(userInput.id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        entity.setName(userInput.name());
        entity.setEmail(userInput.email());
        entity.setLogin(userInput.login());
        entity.setActive(userInput.active());
        entity.setRole(userInput.role());

        if (userInput.password() != null && !userInput.password().isBlank()) {
            entity.setPassword(userInput.password());
        }

        User updated = userRepository.save(entity);
        return toDto(updated);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    private UserDto toDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getLogin(),
                entity.isActive(),
                entity.getRole()
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public boolean existsByLogin(String login) {
        return userRepository.existsByLoginIgnoreCase(login);
    }
}
