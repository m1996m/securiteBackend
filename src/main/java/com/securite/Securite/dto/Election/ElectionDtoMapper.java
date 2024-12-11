package com.securite.Securite.dto.Election;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.Election;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.service.ElectionService;
import com.securite.Securite.service.OrganismeService;
import org.springframework.stereotype.Component;

@Component
public class ElectionDtoMapper implements GenericDtoMapper<ElectionDto, Election> {
    private final ElectionService electionService;
    private final OrganismeService organismeService;

    public ElectionDtoMapper(ElectionService electionService, OrganismeService organismeService) {
        this.electionService = electionService;
        this.organismeService = organismeService;
    }


    @Override
    public ElectionDto toDto(Election election) {
        return ElectionDto.builder()
                .idElection(election.getIdElection())
                .name(election.getName())
                .slug(election.getSlug())
                .dateEnd(election.getDateEnd())
                .typeElection(election.getTypeElection())
                .slugOrganisme(election.getSlugOrganisme())
                .dateStart(election.getDateStart())
                .build();
    }

    @Override
    public Election toEntity(ElectionDto electionDto) {
        Organisme organisme = organismeService.findUniqueById(electionDto.getIdOrganisme());

        if (electionDto.getSlug() != null){
            Election election = electionService
                    .findUniqueWithValueAndAttribut(electionDto.getSlug(), "slug");

            return electionDto.update(electionDto, organisme, election);
        }else {
            electionDto.setSlugOrganisme(organisme.getSlug());

            return electionDto.create(electionDto, organisme);
        }
    }
}
