package com.securite.Securite.dto.vote;

import com.securite.Securite.model.Candidat;
import com.securite.Securite.model.Electeur;
import com.securite.Securite.model.Election;
import com.securite.Securite.model.Vote;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteDto {
    private long idVote;
    private Date dateVote;
    private String slug;
    private LocalDateTime createdAt;
    private long idElection;
    private long idElecteur;
    private long idCandidat;
    private String slugOrganisme;

   public Vote create(VoteDto voteDto,Election election, Electeur electeur, Candidat candidat){
       Vote vote = new Vote();
       vote.setDateVote(voteDto.getDateVote());
       vote.setElecteur(electeur);
       vote.setElection(election);
       vote.setCandidat(candidat);
       vote.setSlugOrganisme(voteDto.slugOrganisme);

       return vote;
   }

    public Vote update(VoteDto voteDto, Candidat candidat, Vote vote){
        vote.setDateVote(voteDto.getDateVote());
        vote.setCandidat(candidat);

        return vote;
    }
}
