package com.securite.Securite.controller;

import com.securite.Securite.dto.carteIdentite.CarteIdentiteDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.CarteIdentite;
import com.securite.Securite.service.CarteIdentiteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carte/identite")
public class CarteIdentiteController extends GenericController<CarteIdentite, CarteIdentiteDto> {
    private final CarteIdentiteService carteIdentiteService;

    public CarteIdentiteController(
            GenericService<CarteIdentite> genericService,
            GenericDtoMapper<CarteIdentiteDto, CarteIdentite> genericDtoMapper,
            CarteIdentiteService carteIdentiteService
    ) {
        super(genericService, genericDtoMapper);
        this.carteIdentiteService = carteIdentiteService;
    }

    @Operation(summary = "Get Carte d'identité by cardNumber")
    @GetMapping("/number")
    public CarteIdentite getCarteIdentiteByCarteNumber(@RequestParam("cardNumber") String cardNumber){
        return carteIdentiteService.findUniqueWithValueAndAttribut(cardNumber, "cardNumber");
    }
}
