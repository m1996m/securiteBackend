package com.securite.Securite.controller;

import com.securite.Securite.dto.Election.ElectionDto;
import com.securite.Securite.enumeration.TypeElection;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Election;
import com.securite.Securite.service.ElectionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/election")
public class ElectionController extends GenericController<Election, ElectionDto> {
    private final ElectionService electionService;

    public ElectionController(
            GenericService<Election> genericService,
            GenericDtoMapper<ElectionDto, Election> genericDtoMapper,
            ElectionService electionService
    ) {
        super(genericService, genericDtoMapper);
        this.electionService = electionService;
    }

    @Operation(summary = "Get List election with type")
    @GetMapping("/type")
    public List<Election> getListElectionBySlug(
            @RequestParam("typeElection") String typeElection,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        TypeElection typeFormate = TypeElection.valueOf(typeElection.toUpperCase());

        return electionService.findAllByAttributeNameAndValue(typeFormate.ordinal(),"typeElection", page, size);
    }
}
