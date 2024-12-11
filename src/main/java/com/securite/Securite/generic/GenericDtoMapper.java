package com.securite.Securite.generic;

public interface GenericDtoMapper<DTO,Entity> {
    DTO toDto(Entity entity);
    Entity toEntity(DTO dto);
}
