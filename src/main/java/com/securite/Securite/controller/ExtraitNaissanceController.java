package com.securite.Securite.controller;

import com.securite.Securite.dto.extraitNaissance.ExtraitNaissanceDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.CarteElectorale;
import com.securite.Securite.model.ExtraitNaissance;
import com.securite.Securite.service.ExtraitNaissanceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/extrait/naissance")
public class ExtraitNaissanceController extends GenericController<ExtraitNaissance, ExtraitNaissanceDto> {
    private final ExtraitNaissanceService extraitNaissanceService;

    public ExtraitNaissanceController(
            GenericService<ExtraitNaissance> genericService,
            GenericDtoMapper<ExtraitNaissanceDto, ExtraitNaissance> genericDtoMapper,
            ExtraitNaissanceService extraitNaissanceService
    ) {
        super(genericService, genericDtoMapper);
        this.extraitNaissanceService = extraitNaissanceService;
    }

    @Operation(summary = "Get extrait de naissance by extraitNumber")
    @GetMapping("/number")
    public ExtraitNaissance getExtraitByCarteNumber(@RequestParam("extraitNumber") String extraitNumber){
        return extraitNaissanceService.findUniqueWithValueAndAttribut(extraitNumber, "extraitNumber");
    }
}
