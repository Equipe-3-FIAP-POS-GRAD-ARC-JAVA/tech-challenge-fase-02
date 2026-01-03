package br.com.fiap.challenge.restautant.infra.gateway;

import br.com.fiap.challenge.restautant.core.dto.UserTypeDto;
import br.com.fiap.challenge.restautant.core.dto.UserTypeInput;
import br.com.fiap.challenge.restautant.core.gateway.UserTypeGateway;
import br.com.fiap.challenge.restautant.infra.entity.UserType;
import br.com.fiap.challenge.restautant.infra.repository.UserTypeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserTypeGatewayAdapter implements UserTypeGateway {

    private final UserTypeRepository userTypeRepository;

    public UserTypeGatewayAdapter(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserTypeDto createUserType(UserTypeInput userTypeInput) {
        UserType entity = new UserType(userTypeInput.name());
        UserType saved = userTypeRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public List<UserTypeDto> getAllUserTypes() {
        return userTypeRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserType(UUID userTypeId) {
        userTypeRepository.deleteById(userTypeId);
    }

    @Override
    public UserTypeDto getUserTypeById(UUID userTypeId) {
        return userTypeRepository.findById(userTypeId)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public UserTypeDto updateUserType(UserTypeInput userTypeInput) {
        UserType entity = userTypeRepository.findById(userTypeInput.id())
                .orElseThrow(() -> new RuntimeException("UserType not found"));
        entity.setName(userTypeInput.name());
        UserType updated = userTypeRepository.save(entity);
        return toDto(updated);
    }

    private UserTypeDto toDto(UserType entity) {
        return new UserTypeDto(entity.getId(), entity.getName());
    }
}