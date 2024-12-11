package com.securite.Securite.controller;

import com.securite.Securite.dto.demande.DemandeDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Demande;
import com.securite.Securite.service.DemandeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demande")
public class DemandeController extends GenericController<Demande, DemandeDto> {
    private final DemandeService demandeService;

    public DemandeController(
            GenericService<Demande> genericService,
            GenericDtoMapper<DemandeDto, Demande> genericDtoMapper,
            DemandeService demandeService
    ) {
        super(genericService, genericDtoMapper);
        this.demandeService = demandeService;
    }

    @Operation(summary = "Get demande by number")
    @GetMapping("/number")
    public Demande getDemandeByCarteNumber(@RequestParam("numeroDemande") String numeroDemande){
        return demandeService.findUniqueWithValueAndAttribut(numeroDemande, "numeroDemande");
    }
}
