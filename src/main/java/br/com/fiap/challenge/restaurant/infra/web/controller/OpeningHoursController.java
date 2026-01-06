package br.com.fiap.challenge.restaurant.infra.web.controller;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.usecase.openinghours.CreateOpeningHours;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.ListOpeningHoursByRestaurant;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.UpdateOpeningHours;
import br.com.fiap.challenge.restaurant.infra.web.api.OpeningHoursAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursInput;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursUpdateInput;



@RestController
@RequestMapping("/api/opening-hours")
public class OpeningHoursController implements OpeningHoursAPI {

    private final CreateOpeningHours createOpeningHours;
    private final UpdateOpeningHours updateOpeningHours;
    private final ListOpeningHoursByRestaurant listOpeningHoursByRestaurant;

    public OpeningHoursController(CreateOpeningHours createOpeningHours,
                                  UpdateOpeningHours updateOpeningHours,
                                  ListOpeningHoursByRestaurant listOpeningHoursByRestaurant) {
        this.createOpeningHours = createOpeningHours;
        this.updateOpeningHours = updateOpeningHours;
        this.listOpeningHoursByRestaurant = listOpeningHoursByRestaurant;
    }

    @PostMapping
    public ResponseEntity<OpeningHoursDto> create(@RequestBody OpeningHoursInput input) {
        OpeningHoursDto result = createOpeningHours.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OpeningHoursDto>> listByRestaurant(@PathVariable UUID restaurantId) {
        List<OpeningHoursDto> result = listOpeningHoursByRestaurant.execute(restaurantId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpeningHoursDto> update(@PathVariable UUID id,
                                                  @RequestBody OpeningHoursUpdateInput input) {
        // garante que o id do path é o que vale (mesmo padrão do FoodTypeController)
        OpeningHoursUpdateInput updatedInput = new OpeningHoursUpdateInput(
                id,
                input.restaurantId(),
                input.weekda(),     // se no seu record for weekday(), troque aqui também
                input.opensAt(),
                input.closesAt(),
                input.isClosed()
        );

        OpeningHoursDto result = updateOpeningHours.execute(updatedInput);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        return null;
    }
}
