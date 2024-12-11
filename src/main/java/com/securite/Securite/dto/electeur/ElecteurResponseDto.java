package com.securite.Securite.dto.electeur;

import com.securite.Securite.dto.carteElectorale.CarteElectoraleResponseDto;
import com.securite.Securite.dto.vote.VoteResponseDto;
import com.securite.Securite.model.CarteElectorale;
import com.securite.Securite.model.Electeur;
import com.securite.Securite.model.Person;
import com.securite.Securite.model.Vote;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ElecteurResponseDto {
    private long idElecteur;
    private Date dateInscription;
    private boolean statutIscription;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idPerson;
    private List<VoteResponseDto> votes;
    private List<CarteElectoraleResponseDto> carteElectorales;
}
