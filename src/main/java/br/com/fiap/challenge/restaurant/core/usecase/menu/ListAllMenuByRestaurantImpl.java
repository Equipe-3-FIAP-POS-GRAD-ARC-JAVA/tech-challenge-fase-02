package br.com.fiap.challenge.restaurant.core.usecase.menu;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.gateway.MenuGateway;

public class ListAllMenuByRestaurantImpl implements ListAllMenuByRestaurant {

    private final MenuGateway menuGateway;

    public ListAllMenuByRestaurantImpl(MenuGateway menuGateway) {
        this.menuGateway = menuGateway;
    }

    @Override
    public List<MenuDto> execute(UUID restaurantId) {
        return menuGateway.getMenusByRestaurantId(restaurantId);
    }

}