package com.securite.Securite.dto.candidat;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.Candidat;
import com.securite.Securite.model.Election;
import com.securite.Securite.service.CandidatService;
import com.securite.Securite.service.ElectionService;
import org.springframework.stereotype.Component;

@Component
public class CandidatDtoMapper implements GenericDtoMapper<CandidatDto, Candidat> {
    private final CandidatService candidatService;
    private final ElectionService electionService;

    public CandidatDtoMapper(CandidatService candidatService, ElectionService electionService) {
        this.candidatService = candidatService;
        this.electionService = electionService;
    }

    @Override
    public CandidatDto toDto(Candidat candidat) {
        return CandidatDto.builder()
                .idCandidat(candidat.getIdCandidat())
                .firtName(candidat.getFirtName())
                .lastName(candidat.getLastName())
                .partiPolitique(candidat.getPartiPolitique())
                .idElection(candidat.getElection().getIdElection())
                .createdAt(candidat.getCreatedAt())
                .image(candidat.getImage())
                .slug(candidat.getSlug())
                .slugOrganisme(candidat.getSlugOrganisme())
                .build();
    }

    @Override
    public Candidat toEntity(CandidatDto dto) {
        Election election = electionService.findUniqueById(dto.getIdElection());

        if (dto.getSlug() != null) {
            Candidat candidat = candidatService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, candidat, election);
        }else {
            dto.setSlugOrganisme(election.getSlugOrganisme());
            return dto.create(dto, election);
        }
    }
}
