package com.securite.Securite.controller;

import com.securite.Securite.dto.organisme.OrganismeDto;
import com.securite.Securite.dto.organisme.OrganismeDtoMapper;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PartController {
    private final PartService partService;
    private final OrganismeDtoMapper organismeDtoMapper;

    @PostMapping("/organisme/part")
    public OrganismeDto create(@RequestBody OrganismeDto dto){
        Organisme organisme = organismeDtoMapper.toEntity(dto);

        Organisme organismeSave = partService.create(organisme);

        return organismeDtoMapper.toDto(organismeSave);
    }
}
