package com.securite.Securite.controller;

import com.securite.Securite.dto.carteElectorale.CarteElectoraleDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.CarteElectorale;
import com.securite.Securite.service.CarteElectoraleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carte/electorale")
public class CarteElectoraleController extends GenericController<CarteElectorale, CarteElectoraleDto> {
    private final CarteElectoraleService carteElectoraleService;

    public CarteElectoraleController(
            GenericService<CarteElectorale> genericService,
            GenericDtoMapper<CarteElectoraleDto, CarteElectorale> genericDtoMapper,
            CarteElectoraleService carteElectoraleService
    ) {
        super(genericService, genericDtoMapper);
        this.carteElectoraleService = carteElectoraleService;
    }

    @Operation(summary = "Get Carte electorale by cateNumber")
    @GetMapping("/number")
    public CarteElectorale getCarteElectoraleByCarteNumber(@RequestParam("carteNumber") String carteNumber){
        return carteElectoraleService.findUniqueWithValueAndAttribut(carteNumber, "carteNumber");
    }
}
