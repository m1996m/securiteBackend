package com.securite.Securite.dto.electeur;

import com.securite.Securite.dto.carteElectorale.CarteElectoraleResponseDtoMapper;
import com.securite.Securite.dto.vote.VoteResponseDtoMapper;
import com.securite.Securite.generic.GenericResponseDtoMapper;
import com.securite.Securite.model.Electeur;
import org.springframework.stereotype.Component;

@Component
public class ElecteurResponseDtoMapper implements
        GenericResponseDtoMapper<ElecteurResponseDto, Electeur> {
    private final VoteResponseDtoMapper voteMapper;
    private final CarteElectoraleResponseDtoMapper carteMapper;

    public ElecteurResponseDtoMapper(VoteResponseDtoMapper voteMapper, CarteElectoraleResponseDtoMapper carteMapper) {
        this.voteMapper = voteMapper;
        this.carteMapper = carteMapper;
    }

    @Override
    public ElecteurResponseDto toResponseDto(Electeur electeur) {
        return ElecteurResponseDto.builder()
                .idElecteur(electeur.getIdElecteur())
                .slugOrganisme(electeur.getSlugOrganisme())
                .slug(electeur.getSlug())
                .dateInscription(electeur.getDateInscription())
                .createdAt(electeur.getCreatedAt())
                .idPerson(electeur.getPerson().getIdPerson())
                .votes(electeur.getVotes().stream().map(voteMapper::toResponseDto).toList())
                .carteElectorales(electeur.getCarteElectorales().stream().map(carteMapper::toResponseDto).toList())
                .statutIscription(electeur.isStatutIscription())
                .build();
    }
}
