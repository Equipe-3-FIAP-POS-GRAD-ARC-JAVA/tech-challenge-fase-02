package br.com.fiap.challenge.restaurant.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.dto.MenuInput;

public interface MenuGateway {

    List<MenuDto> getAllMenus();

    MenuDto getMenuById(UUID menuId);

    List<MenuDto> getMenusByRestaurantId(UUID restaurantId);

    MenuDto createMenu(MenuInput menu);

    MenuDto updateMenu(MenuInput menu);

    void deleteMenu(UUID menuId);

}
