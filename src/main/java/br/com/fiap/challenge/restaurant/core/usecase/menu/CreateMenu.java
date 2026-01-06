package br.com.fiap.challenge.restaurant.core.usecase.menu;

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.dto.MenuInput;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface CreateMenu extends UseCase<MenuInput, MenuDto> {
    MenuDto execute(MenuInput menuInput);
}