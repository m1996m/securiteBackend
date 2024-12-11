package com.securite.Securite.dto.candidat;

import com.securite.Securite.model.Candidat;
import com.securite.Securite.model.Election;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CandidatDto {
    private long idCandidat;
    private String firtName;
    private String lastName;
    private String partiPolitique;
    private String slug;
    private String image;
    private LocalDateTime createdAt;
    private long idElection;
    private String slugOrganisme;


    public Candidat create(CandidatDto candidatDto, Election election){
       Candidat candidat = new Candidat();
       candidat.setFirtName(candidatDto.getFirtName());
       candidat.setLastName(candidatDto.getLastName());
       candidat.setPartiPolitique(candidatDto.getPartiPolitique());
       candidat.setFirtName(candidatDto.getFirtName());
       candidat.setElection(election);
       candidat.setSlugOrganisme(candidatDto.slugOrganisme);

       return candidat;
   }

    public Candidat update(CandidatDto candidatDto, Candidat candidat, Election election){
        candidat.setFirtName(candidatDto.getFirtName());
        candidat.setLastName(candidatDto.getLastName());
        candidat.setPartiPolitique(candidatDto.getPartiPolitique());
        candidat.setFirtName(candidatDto.getFirtName());
        candidat.setElection(election);

        return candidat;
    }
}
