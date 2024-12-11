package com.securite.Securite.dto.Election;

import com.securite.Securite.generic.GenericResponseDtoMapper;
import com.securite.Securite.model.Election;
import org.springframework.stereotype.Component;

@Component
public class ElectionResponseDtoMapper implements
        GenericResponseDtoMapper<ElectionResponseDto, Election> {
    @Override
    public ElectionResponseDto toResponseDto(Election election) {
        return ElectionResponseDto.builder()
                .idElection(election.getIdElection())
                .name(election.getName())
                .slug(election.getSlug())
                .dateEnd(election.getDateEnd())
                .typeElection(election.getTypeElection())
                .slugOrganisme(election.getSlugOrganisme())
                .dateStart(election.getDateStart())
                .build();
    }
}
