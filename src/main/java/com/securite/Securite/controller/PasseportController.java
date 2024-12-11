package com.securite.Securite.controller;

import com.securite.Securite.dto.passeport.PasseportDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.ExtraitNaissance;
import com.securite.Securite.model.Passeport;
import com.securite.Securite.repository.Generic.PasseportDAO;
import com.securite.Securite.service.PasseportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passeport")
public class PasseportController extends GenericController<Passeport, PasseportDto> {
    private final PasseportService passeportService;

    public PasseportController(
            GenericService<Passeport> genericService,
            GenericDtoMapper<PasseportDto, Passeport> genericDtoMapper,
            PasseportService passeportService
    ) {
        super(genericService, genericDtoMapper);
        this.passeportService = passeportService;
    }

    @Operation(summary = "Get passeport by passportNumber")
    @GetMapping("/number")
    public Passeport getPasseportByPassportNumber(@RequestParam("passportNumber") String passportNumber){
        return passeportService.findUniqueWithValueAndAttribut(passportNumber, "passportNumber");
    }
}
