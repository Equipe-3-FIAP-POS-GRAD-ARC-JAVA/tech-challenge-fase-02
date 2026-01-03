package br.com.fiap.challenge.restautant.core.usecase.menu;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.gateway.MenuGateway;

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