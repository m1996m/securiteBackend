package com.securite.Securite.controller;

import com.securite.Securite.dto.electeur.ElecteurDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Electeur;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/electeur")
public class ElecteurController extends GenericController<Electeur, ElecteurDto> {
    public ElecteurController(GenericService<Electeur> genericService, GenericDtoMapper<ElecteurDto, Electeur> genericDtoMapper) {
        super(genericService, genericDtoMapper);
    }
}
