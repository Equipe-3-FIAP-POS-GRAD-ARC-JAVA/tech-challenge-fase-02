package br.com.fiap.challenge.restautant.infra.gateway;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.dto.MenuInput;
import br.com.fiap.challenge.restautant.core.gateway.MenuGateway;
import br.com.fiap.challenge.restautant.infra.entity.Menu;
import br.com.fiap.challenge.restautant.infra.entity.Restaurant;
import br.com.fiap.challenge.restautant.infra.repository.MenuRepository;
import br.com.fiap.challenge.restautant.infra.repository.RestaurantRepository;

@Component
public class MenuGatewayAdapter implements MenuGateway {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuGatewayAdapter(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<MenuDto> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDto getMenuById(UUID menuId) {
        return menuRepository.findById(menuId)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public List<MenuDto> getMenusByRestaurantId(UUID restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDto createMenu(MenuInput menuInput) {
        Restaurant restaurant = restaurantRepository.findById(menuInput.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        Menu entity = new Menu(restaurant, null); // foods will be added separately
        Menu saved = menuRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public MenuDto updateMenu(MenuInput menuInput) {
        Menu entity = menuRepository.findById(menuInput.id())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        Restaurant restaurant = restaurantRepository.findById(menuInput.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        entity.setRestaurant(restaurant);
        Menu updated = menuRepository.save(entity);
        return toDto(updated);
    }

    @Override
    public void deleteMenu(UUID menuId) {
        menuRepository.deleteById(menuId);
    }

    private MenuDto toDto(Menu entity) {
        return new MenuDto(entity.getId(), entity.getRestaurant().getId(), null); // foods not included
    }
}