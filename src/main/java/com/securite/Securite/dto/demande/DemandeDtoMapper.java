package com.securite.Securite.dto.demande;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.Demande;
import com.securite.Securite.model.Person;
import com.securite.Securite.model.Sequence_table;
import com.securite.Securite.service.DemandeService;
import com.securite.Securite.service.PersonService;
import com.securite.Securite.service.Sequence_tableService;
import org.springframework.stereotype.Component;

@Component
public class DemandeDtoMapper implements GenericDtoMapper<DemandeDto, Demande> {
    private final DemandeService demandeService;
    private final PersonService personService;
    private final Sequence_tableService sequenceTableService;

    public DemandeDtoMapper(DemandeService demandeService, PersonService personService, Sequence_tableService sequenceTableService) {
        this.demandeService = demandeService;
        this.personService = personService;
        this.sequenceTableService = sequenceTableService;
    }

    @Override
    public DemandeDto toDto(Demande demande) {
        return DemandeDto.builder()
                .idDemande(demande.getIdDemande())
                .dateDemande(demande.getDateDemande())
                .createdAt(demande.getCreatedAt())
                .dateValidation(demande.getDateValidation())
                .idPerson(demande.getPerson().getIdPerson())
                .slug(demande.getSlug())
                .slugOrganisme(demande.getSlugOrganisme())
                .statut(demande.getStatut())
                .typeDocument(demande.getTypeDocument())
                .build();
    }

    @Override
    public Demande toEntity(DemandeDto dto) {
        Person person = personService.findUniqueById(dto.getIdPerson());

        if (dto.getSlug() != null) {
            Demande demande = demandeService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, person, demande);
        }else {
            Sequence_table sequenceTable = sequenceTableService.find("demande");

            sequenceTableService.update(sequenceTable);

            dto.setNumeroDemande(sequenceTable.getNext_value());
            dto.setCodeUnique(person.getCodeUnique());

            return dto.create(dto, person);
        }
    }
}
