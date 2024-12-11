package com.securite.Securite.dto.candidat;

import com.securite.Securite.dto.vote.VoteResponseDtoMapper;
import com.securite.Securite.generic.GenericResponseDtoMapper;
import com.securite.Securite.model.Candidat;
import org.springframework.stereotype.Component;

@Component
public class CandidatResponseDtoMapper implements GenericResponseDtoMapper<CandidatResponseDto, Candidat> {
    private final VoteResponseDtoMapper voteMapper;

    public CandidatResponseDtoMapper(VoteResponseDtoMapper voteResponseDtoMapper) {
        this.voteMapper = voteResponseDtoMapper;
    }

    @Override
    public CandidatResponseDto toResponseDto(Candidat candidat) {
        return CandidatResponseDto.builder()
                .idCandidat(candidat.getIdCandidat())
                .firtName(candidat.getFirtName())
                .lastName(candidat.getLastName())
                .partiPolitique(candidat.getPartiPolitique())
                .idElection(candidat.getElection().getIdElection())
                .createdAt(candidat.getCreatedAt())
                .image(candidat.getImage())
                .slug(candidat.getSlug())
                .slugOrganisme(candidat.getSlugOrganisme())
                .vote(candidat.getVotes().stream().map(voteMapper::toResponseDto).toList())
                .build();
    }
}
