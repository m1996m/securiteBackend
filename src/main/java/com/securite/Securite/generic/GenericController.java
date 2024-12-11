package com.securite.Securite.generic;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GenericController<Entity, DTO> {
    private final GenericService<Entity> genericService;
    private final GenericDtoMapper<DTO,Entity> genericDtoMapper;

    public GenericController(GenericService<Entity> genericService, GenericDtoMapper<DTO, Entity> genericDtoMapper) {
        this.genericService = genericService;
        this.genericDtoMapper = genericDtoMapper;
    }

    @Operation(summary = "Ajout d'entity")
    @PostMapping("/create")
    public DTO create(@RequestBody DTO dto){
        Entity entity = genericDtoMapper.toEntity(dto);

        Entity entitySave = genericService.create(entity);

        return genericDtoMapper.toDto(entitySave);
    }

    @Operation(summary = "Update d'entity")
    @PutMapping("/update/{slug}")
    public DTO update(@RequestBody DTO dto, @PathVariable String slug){
        Entity entity = genericDtoMapper.toEntity(dto);

        Entity entitySave = genericService.update(entity, slug);

        return genericDtoMapper.toDto(entitySave);
    }

    @Operation(summary = "Get List entity with  organisme slug")
    @GetMapping("/list/{slug}")
    public List<Entity> getListEntityOrganismeBySlug(
            @PathVariable String slug,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return genericService.findAllByAttributeNameAndValue(slug,"slugOrganisme", page, size);
    }

    @Operation(summary = "Get entity with slug")
    @GetMapping("/slug/{slug}")
    public Entity getListEntityBySlug(@PathVariable String slug){
        return genericService.findUniqueWithValueAndAttribut(slug,"slug");
    }

    @Operation(summary = "Get entity with id")
    @GetMapping("/id/{id}")
    public Entity getListEntityByOrganismeBySlug(@PathVariable long id){
        return genericService.findUniqueById(id);
    }

    @Operation(summary = "Get entity with slug")
    @DeleteMapping("/delete/{slug}")
    public String deleteBySlug(@PathVariable String slug){
        return genericService.delete(slug);
    }
}
