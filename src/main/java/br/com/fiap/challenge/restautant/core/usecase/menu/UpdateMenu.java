package br.com.fiap.challenge.restautant.core.usecase.menu;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.dto.MenuInput;
import br.com.fiap.challenge.restautant.core.usecase.base.UseCase;

public interface UpdateMenu extends UseCase<MenuInput, MenuDto> {
    MenuDto execute(MenuInput menuInput);
}