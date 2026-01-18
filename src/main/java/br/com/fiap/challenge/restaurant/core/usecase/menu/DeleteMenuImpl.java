package br.com.fiap.challenge.restaurant.core.usecase.menu;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.gateway.MenuGateway;

public class DeleteMenuImpl implements DeleteMenu {

    private final MenuGateway menuGateway;

    public DeleteMenuImpl(MenuGateway menuGateway) {
        this.menuGateway = menuGateway;
    }

    @Override
    public void execute(UUID menuId) {
        menuGateway.deleteMenu(menuId);
    }

}