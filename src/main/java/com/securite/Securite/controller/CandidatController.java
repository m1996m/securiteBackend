package com.securite.Securite.controller;

import com.securite.Securite.dto.candidat.CandidatDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Candidat;
import com.securite.Securite.service.CandidatService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidat")
public class CandidatController extends GenericController<Candidat, CandidatDto> {
    private final CandidatService candidatService;

    public CandidatController(
            GenericService<Candidat> genericService,
            GenericDtoMapper<CandidatDto, Candidat> genericDtoMapper,
            CandidatService candidatService
    ) {
        super(genericService, genericDtoMapper);
        this.candidatService = candidatService;
    }

    @Operation(summary = "Get Candidat by parti politique")
    @GetMapping("/parti/politique")
    public Candidat getCandidatByPartiPolique(@RequestParam("partiPolitique") String parti){
        return candidatService.findUniqueWithValueAndAttribut(parti, "partiPolitique");
    }

}
