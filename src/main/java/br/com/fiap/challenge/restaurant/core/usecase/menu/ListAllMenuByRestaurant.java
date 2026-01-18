package br.com.fiap.challenge.restaurant.core.usecase.menu;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface ListAllMenuByRestaurant extends UseCase<UUID, List<MenuDto>> {
    List<MenuDto> execute(UUID restaurantId);
}