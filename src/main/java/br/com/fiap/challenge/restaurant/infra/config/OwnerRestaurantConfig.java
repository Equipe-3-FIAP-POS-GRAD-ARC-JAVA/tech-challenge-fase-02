package br.com.fiap.challenge.restaurant.infra.config;

import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.challenge.restaurant.core.gateway.OwnerRestaurantGateway;

@Configuration
public class OwnerRestaurantConfig {

    @Bean
    LinkOwnerRestaurant linkOwnerRestaurant(OwnerRestaurantGateway gateway) {
        return new br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.LinkOwnerRestaurantImpl(gateway);
    }

    @Bean
    UnlinkOwnerRestaurant unlinkOwnerRestaurant(OwnerRestaurantGateway gateway) {
        return new UnlinkOwnerRestaurantImpl(gateway);
    }

    @Bean
    ListOwnerRestaurantByOwner listOwnerRestaurantByOwner(OwnerRestaurantGateway gateway) {
        return new ListOwnerRestaurantByOwnerImpl(gateway);
    }

    @Bean
    ListOwnerRestaurantByRestaurant listOwnerRestaurantByRestaurant(OwnerRestaurantGateway gateway) {
        return new ListOwnerRestaurantByRestaurantImpl(gateway);
    }
}
