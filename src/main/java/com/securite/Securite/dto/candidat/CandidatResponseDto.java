package com.securite.Securite.dto.candidat;

import com.securite.Securite.dto.vote.VoteResponseDto;
import com.securite.Securite.model.Candidat;
import com.securite.Securite.model.Election;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CandidatResponseDto {
    private long idCandidat;
    private String firtName;
    private String lastName;
    private String partiPolitique;
    private String slug;
    private String image;
    private LocalDateTime createdAt;
    private long idElection;
    private List<VoteResponseDto> vote;
    private String slugOrganisme;
}
