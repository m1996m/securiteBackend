package com.securite.Securite.controller;

import com.securite.Securite.dto.declarationNaissance.DeclarationNaissanceDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.CarteIdentite;
import com.securite.Securite.model.DeclarationNaissance;
import com.securite.Securite.service.DeclarationNaissanceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/declaration/naissance")
public class DeclarationNaissanceController extends GenericController<DeclarationNaissance, DeclarationNaissanceDto> {
    private final DeclarationNaissanceService declarationNaissanceService;

    public DeclarationNaissanceController(
            GenericService<DeclarationNaissance> genericService,
            GenericDtoMapper<DeclarationNaissanceDto, DeclarationNaissance> genericDtoMapper,
            DeclarationNaissanceService declarationNaissanceService
    ) {
        super(genericService, genericDtoMapper);
        this.declarationNaissanceService = declarationNaissanceService;
    }

    @Operation(summary = "Get declaration naissance by date and place")
    @GetMapping("/date/place")
    public List<DeclarationNaissance> getCandidatByCarteNumber(
            @RequestParam("declartionDate") String declartionDate,
            @RequestParam("place") String place
    ){
        return declarationNaissanceService.findListAndTwoValue(
                "declartionDate",
                "place",
                declartionDate,
                place
        );
    }
}
