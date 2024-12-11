package com.securite.Securite.dto.carteIdentite;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.CarteIdentite;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.Person;
import com.securite.Securite.model.Sequence_table;
import com.securite.Securite.service.CarteIdentiteService;
import com.securite.Securite.service.OrganismeService;
import com.securite.Securite.service.PersonService;
import com.securite.Securite.service.Sequence_tableService;
import org.springframework.stereotype.Component;

@Component
public class CarteIdentiteDtoMapper implements GenericDtoMapper<CarteIdentiteDto, CarteIdentite> {
    private final OrganismeService organismeService;
    private final PersonService personService;
    private final CarteIdentiteService service;
    private final Sequence_tableService sequenceTableService;

    public CarteIdentiteDtoMapper(
            OrganismeService organismeService,
            PersonService personService,
            CarteIdentiteService service,
            Sequence_tableService sequenceTableService
    ) {
        this.organismeService = organismeService;
        this.personService = personService;
        this.service = service;
        this.sequenceTableService = sequenceTableService;
    }

    @Override
    public CarteIdentiteDto toDto(CarteIdentite carteIdentite) {
        return CarteIdentiteDto.builder()
                .idCardIdentity(carteIdentite.getIdCardIdentity())
                .slug(carteIdentite.getSlug())
                .cardNumber(carteIdentite.getCardNumber())
                .createdAt(carteIdentite.getCreatedAt())
                .delivranceDate(carteIdentite.getDelivranceDate())
                .expirationDate(carteIdentite.getExpirationDate())
                .idOrganisme(carteIdentite.getOrganisme().getIdOrganisme())
                .idPerson(carteIdentite.getPerson().getIdPerson())
                .slugOrganisme(carteIdentite.getSlugOrganisme())
                .build();
    }

    @Override
    public CarteIdentite toEntity(CarteIdentiteDto dto) {
        Person person = personService.findUniqueById(dto.getIdPerson());
        Organisme organisme = organismeService.findUniqueById(dto.getIdOrganisme());

        if (dto.getSlug() != null) {
            CarteIdentite carteIdentite = service.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, organisme, person, carteIdentite);
        }else {
            Sequence_table sequenceTable = sequenceTableService.find("carteIdentite");

            sequenceTableService.update(sequenceTable);

            dto.setCardNumber(sequenceTable.getNext_value());
            dto.setCodeUnique(person.getCodeUnique());
            dto.setSlugOrganisme(organisme.getSlug());

            return dto.create(dto, organisme, person);
        }
    }
}
