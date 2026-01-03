package br.com.fiap.challenge.restautant.core.usecase.menu;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.dto.MenuInput;
import br.com.fiap.challenge.restautant.core.gateway.MenuGateway;

public class UpdateMenuImpl implements UpdateMenu {

    private final MenuGateway menuGateway;

    public UpdateMenuImpl(MenuGateway menuGateway) {
        this.menuGateway = menuGateway;
    }

    @Override
    public MenuDto execute(MenuInput menuInput) {
        return menuGateway.updateMenu(menuInput);
    }

}