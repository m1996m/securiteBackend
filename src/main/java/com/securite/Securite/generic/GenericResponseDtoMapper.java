package com.securite.Securite.generic;

public interface GenericResponseDtoMapper<ResponseDTO,Entity> {
    ResponseDTO toResponseDto(Entity entity);
}
