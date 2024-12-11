package com.securite.Securite.dto.vote;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.Candidat;
import com.securite.Securite.model.Electeur;
import com.securite.Securite.model.Election;
import com.securite.Securite.model.Vote;
import com.securite.Securite.service.CandidatService;
import com.securite.Securite.service.ElecteurService;
import com.securite.Securite.service.ElectionService;
import com.securite.Securite.service.VoteService;
import org.springframework.stereotype.Component;

@Component
public class VoteDtoMapper implements GenericDtoMapper<VoteDto, Vote> {
    private final ElecteurService electeurService;
    private final ElectionService electionService;
    private final CandidatService candidatService;
    private final VoteService voteService;

    public VoteDtoMapper(
            ElecteurService electeurService,
            ElectionService electionService,
            CandidatService candidatService,
            VoteService voteService
    ) {
        this.electeurService = electeurService;
        this.electionService = electionService;
        this.candidatService = candidatService;
        this.voteService = voteService;
    }

    @Override
    public VoteDto toDto(Vote vote) {
        return VoteDto.builder()
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

    @Override
    public Vote toEntity(VoteDto voteDto) {
        Candidat candidat = candidatService.findUniqueById(voteDto.getIdCandidat());

        if (voteDto.getSlug() != null){
            Vote vote = voteService.findUniqueWithValueAndAttribut(voteDto.getSlug(), "slug");

            return voteDto.update(voteDto, candidat, vote);
        }else {
            Electeur electeur = electeurService.findUniqueById(voteDto.getIdElecteur());
            Election election = electionService.findUniqueById(voteDto.getIdElection());

            return voteDto.create(voteDto, election, electeur, candidat);
        }
    }
}
