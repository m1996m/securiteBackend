package com.securite.Securite.dto.carteElectorale;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.CarteElectorale;
import com.securite.Securite.model.Electeur;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.Sequence_table;
import com.securite.Securite.service.CarteElectoraleService;
import com.securite.Securite.service.ElecteurService;
import com.securite.Securite.service.OrganismeService;
import com.securite.Securite.service.Sequence_tableService;
import org.springframework.stereotype.Component;

@Component
public class CarteElectoraleDtoMapper implements GenericDtoMapper<CarteElectoraleDto, CarteElectorale> {
    private final CarteElectoraleService carte;
    private final ElecteurService electeurService;
    private final OrganismeService organismeService;
    private final Sequence_tableService sequence;

    public CarteElectoraleDtoMapper(
            CarteElectoraleService carte,
            ElecteurService electeurService,
            OrganismeService organismeService, Sequence_tableService sequence
    ) {
        this.carte = carte;
        this.electeurService = electeurService;
        this.organismeService = organismeService;
        this.sequence = sequence;
    }

    @Override
    public CarteElectoraleDto toDto(CarteElectorale carteElectorale) {
        return CarteElectoraleDto.builder()
                .idCarteElectorale(carteElectorale.getIdCarteElectorale())
                .carteNumber(carteElectorale.getCarteNumber())
                .slug(carteElectorale.getSlug())
                .createdAt(carteElectorale.getCreatedAt())
                .emissionDate(carteElectorale.getEmissionDate())
                .expirationDate(carteElectorale.getExpirationDate())
                .idElecteur(carteElectorale.getElecteur().getIdElecteur())
                .idOrganisme(carteElectorale.getOrganisme().getIdOrganisme())
                .slugOrganisme(carteElectorale.getSlugOrganisme())
                .slug(carteElectorale.getSlug())
                .build();
    }

    @Override
    public CarteElectorale toEntity(CarteElectoraleDto dto) {
        Electeur electeur = electeurService.findUniqueById(dto.getIdElecteur());
        Organisme organisme = organismeService.findUniqueById(dto.getIdOrganisme());

        if (dto.getSlug() != null) {
            CarteElectorale carteElectorale = carte.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, carteElectorale);
        }else {
            Sequence_table sequenceTable = sequence.find("carteElectorale");

            dto.setSlugOrganisme(organisme.getSlug());
            dto.setCodeUnique(electeur.getPerson().getCodeUnique());
            dto.setCarteNumber(sequenceTable.getNext_value());

            sequence.update(sequenceTable);

            return dto.create(dto, electeur, organisme);
        }
    }
}
