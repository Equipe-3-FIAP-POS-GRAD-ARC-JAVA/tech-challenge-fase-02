package br.com.fiap.challenge.restautant.core.usecase.menu;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.gateway.MenuGateway;

import java.util.List;

public class ListAllMenuImpl implements ListAllMenu {

    private final MenuGateway menuGateway;

    public ListAllMenuImpl(MenuGateway menuGateway) {
        this.menuGateway = menuGateway;
    }

    @Override
    public List<MenuDto> execute() {
        return menuGateway.getAllMenus();
    }
}