package br.com.fiap.challenge.restautant.core.usecase.menu;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.usecase.UseCase;

public interface ListAllMenuByRestaurant extends UseCase<UUID, List<MenuDto>> {
    List<MenuDto> execute(UUID restaurantId);
}