package br.com.fiap.challenge.restaurant.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.challenge.restaurant.core.gateway.MenuGateway;
import br.com.fiap.challenge.restaurant.core.usecase.menu.CreateMenu;
import br.com.fiap.challenge.restaurant.core.usecase.menu.CreateMenuImpl;
import br.com.fiap.challenge.restaurant.core.usecase.menu.DeleteMenu;
import br.com.fiap.challenge.restaurant.core.usecase.menu.DeleteMenuImpl;
import br.com.fiap.challenge.restaurant.core.usecase.menu.ListAllMenu;
import br.com.fiap.challenge.restaurant.core.usecase.menu.ListAllMenuByRestaurant;
import br.com.fiap.challenge.restaurant.core.usecase.menu.ListAllMenuByRestaurantImpl;
import br.com.fiap.challenge.restaurant.core.usecase.menu.ListAllMenuImpl;
import br.com.fiap.challenge.restaurant.core.usecase.menu.ListMenuById;
import br.com.fiap.challenge.restaurant.core.usecase.menu.ListMenuByIdImpl;
import br.com.fiap.challenge.restaurant.core.usecase.menu.UpdateMenu;
import br.com.fiap.challenge.restaurant.core.usecase.menu.UpdateMenuImpl;

@Configuration
public class UseCaseMenuConfig {

    @Bean
    public CreateMenu createMenu(MenuGateway menuGateway) {
        return new CreateMenuImpl(menuGateway);
    }

    @Bean
    public DeleteMenu deleteMenu(MenuGateway menuGateway) {
        return new DeleteMenuImpl(menuGateway);
    }

    @Bean
    public ListAllMenu listAllMenu(MenuGateway menuGateway) {
        return new ListAllMenuImpl(menuGateway);
    }

    @Bean
    public ListAllMenuByRestaurant listAllMenuByRestaurant(MenuGateway menuGateway) {
        return new ListAllMenuByRestaurantImpl(menuGateway);
    }

    @Bean
    public ListMenuById listMenuById(MenuGateway menuGateway) {
        return new ListMenuByIdImpl(menuGateway);
    }

    @Bean
    public UpdateMenu updateMenu(MenuGateway menuGateway) {
        return new UpdateMenuImpl(menuGateway);
    }
}