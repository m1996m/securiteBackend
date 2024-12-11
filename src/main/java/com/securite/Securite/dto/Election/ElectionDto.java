package com.securite.Securite.dto.Election;

import com.securite.Securite.enumeration.TypeElection;
import com.securite.Securite.model.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ElectionDto {
    private long idElection;
    private Date dateStart;
    private Date dateEnd;
    private String name;
    private TypeElection typeElection = TypeElection.PRESIDENTIEL;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;

    public Election create(ElectionDto electionDto, Organisme organisme){
        Election election = new Election();
        election.setName(electionDto.getName());
        election.setDateEnd(electionDto.getDateEnd());
        election.setDateStart(electionDto.getDateStart());
        election.setTypeElection(electionDto.getTypeElection());
        election.setSlugOrganisme(electionDto.getSlugOrganisme());
        election.setOrganisme(organisme);

       return election;
   }

    public Election update(ElectionDto electionDto, Organisme organisme, Election election){
        election.setName(electionDto.getName());
        election.setDateEnd(electionDto.getDateEnd());
        election.setDateStart(electionDto.getDateStart());
        election.setTypeElection(electionDto.getTypeElection());
        election.setSlugOrganisme(electionDto.getSlugOrganisme());
        election.setOrganisme(organisme);

        return election;
    }
}
