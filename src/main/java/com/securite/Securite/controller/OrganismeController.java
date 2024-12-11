package com.securite.Securite.controller;

import com.securite.Securite.dto.organisme.OrganismeDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.service.OrganismeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organisme")
public class OrganismeController extends GenericController<Organisme, OrganismeDto> {
    private final OrganismeService organismeService;
    public OrganismeController(
            @Qualifier("organismeService") GenericService<Organisme> genericService,
            GenericDtoMapper<OrganismeDto, Organisme> genericDtoMapper, OrganismeService organismeService
    ) {
        super(genericService, genericDtoMapper);
        this.organismeService = organismeService;
    }

    @Operation(summary = "Get List Organsime with type")
    @GetMapping("/list/type")
    public List<Organisme> getListOrganismeBySlug(
            @RequestParam("type") String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size
    ){
        return organismeService.findAllByAttributeNameAndValue(type,"type", page, size);
    }

    @Operation(summary = "Get organsime with By email")
    @GetMapping("/email")
    public Organisme getOrganismeByEmail(@RequestParam("email") String email){
        return organismeService.findUniqueWithValueAndAttribut(email,"email");
    }

    @Operation(summary = "Get organsime with By tel")
    @GetMapping("/tel")
    public Organisme getOrganismeByTel(@RequestParam("tel") String tel){
        return organismeService.findUniqueWithValueAndAttribut(tel,"tel");
    }

    @Operation(summary = "Get organsime with By Name")
    @GetMapping("/name")
    public Organisme getOrganismeByName(
            @RequestParam("name") String name
    ){
        return organismeService.findUniqueWithValueAndAttribut(name,"name");
    }
}
