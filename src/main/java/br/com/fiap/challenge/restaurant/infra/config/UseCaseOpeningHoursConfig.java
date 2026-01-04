package br.com.fiap.challenge.restaurant.infra.config;

import br.com.fiap.challenge.restaurant.core.gateway.OpeningHoursGateway;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.CreateOpeningHours;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.CreateOpeningHoursImpl;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.ListOpeningHoursByRestaurant;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.ListOpeningHoursByRestaurantImpl;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.UpdateOpeningHours;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.UpdateOpeningHoursImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseOpeningHoursConfig {

    @Bean
    public CreateOpeningHours createOpeningHours(OpeningHoursGateway openingHoursGateway) {
        return new CreateOpeningHoursImpl(openingHoursGateway);
    }

    @Bean
    public UpdateOpeningHours updateOpeningHours(OpeningHoursGateway openingHoursGateway) {
        return new UpdateOpeningHoursImpl(openingHoursGateway);
    }

    @Bean
    public ListOpeningHoursByRestaurant listOpeningHoursByRestaurant(OpeningHoursGateway openingHoursGateway) {
        return new ListOpeningHoursByRestaurantImpl(openingHoursGateway);
    }
}
