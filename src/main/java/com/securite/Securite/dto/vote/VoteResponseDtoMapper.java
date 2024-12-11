package com.securite.Securite.dto.vote;

import com.securite.Securite.generic.GenericResponseDtoMapper;
import com.securite.Securite.model.Vote;
import org.springframework.stereotype.Component;

@Component
public class VoteResponseDtoMapper implements GenericResponseDtoMapper<VoteResponseDto, Vote> {

    @Override
    public VoteResponseDto toResponseDto(Vote vote) {
        return VoteResponseDto.builder()
                .idVote(vote.getIdVote())
                .slug(vote.getSlug())
                .dateVote(vote.getDateVote())
                .idCandidat(vote.getCandidat().getIdCandidat())
                .idElecteur(vote.getElecteur().getIdElecteur())
                .idElection(vote.getElection().getIdElection())
                .createdAt(vote.getCreatedAt())
                .slugOrganisme(vote.getSlugOrganisme())
                .build();
    }
}
