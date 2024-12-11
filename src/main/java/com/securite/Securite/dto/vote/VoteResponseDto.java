package com.securite.Securite.dto.vote;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteResponseDto {
    private long idVote;
    private Date dateVote;
    private String slug;
    private LocalDateTime createdAt;
    private long idElection;
    private long idElecteur;
    private long idCandidat;
    private String slugOrganisme;
}
