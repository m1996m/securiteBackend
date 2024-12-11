package com.securite.Securite.dto.Election;

import com.securite.Securite.dto.candidat.CandidatResponseDto;
import com.securite.Securite.dto.vote.VoteResponseDto;
import com.securite.Securite.enumeration.TypeElection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ElectionResponseDto {
    private long idElection;
    private Date dateStart;
    private Date dateEnd;
    private String name;
    private TypeElection typeElection = TypeElection.PRESIDENTIEL;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private List<VoteResponseDto> votes;
    private List<CandidatResponseDto> candidats;
}
