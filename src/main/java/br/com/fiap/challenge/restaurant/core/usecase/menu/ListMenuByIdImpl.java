package br.com.fiap.challenge.restaurant.core.usecase.menu;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.gateway.MenuGateway;

public class ListMenuByIdImpl implements ListMenuById {

    private final MenuGateway menuGateway;

    public ListMenuByIdImpl(MenuGateway menuGateway) {
        this.menuGateway = menuGateway;
    }

    @Override
    public MenuDto execute(UUID menuId) {
        return menuGateway.getMenuById(menuId);
    }

}