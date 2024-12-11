package com.securite.Securite.dto.extraitNaissance;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.DeclarationNaissance;
import com.securite.Securite.model.ExtraitNaissance;
import com.securite.Securite.model.Person;
import com.securite.Securite.model.Sequence_table;
import com.securite.Securite.service.DeclarationNaissanceService;
import com.securite.Securite.service.ExtraitNaissanceService;
import com.securite.Securite.service.PersonService;
import com.securite.Securite.service.Sequence_tableService;
import org.springframework.stereotype.Component;

@Component
public class ExtraitNaissanceDtoMapper implements
        GenericDtoMapper<ExtraitNaissanceDto, ExtraitNaissance> {
    private final PersonService personService;
    private final ExtraitNaissanceService extraitNaissanceService;
    private final Sequence_tableService sequenceTableService;
    private final DeclarationNaissanceService declarationService;

    public ExtraitNaissanceDtoMapper(
            PersonService personService,
            ExtraitNaissanceService extraitNaissanceService,
            Sequence_tableService sequenceTableService,
            DeclarationNaissanceService declarationService
    ) {
        this.personService = personService;
        this.extraitNaissanceService = extraitNaissanceService;
        this.sequenceTableService = sequenceTableService;
        this.declarationService = declarationService;
    }

    @Override
    public ExtraitNaissanceDto toDto(ExtraitNaissance extraitNaissance) {
        return ExtraitNaissanceDto.builder()
                .idExtraitNaissance(extraitNaissance.getIdExtraitNaissance())
                .extraitNumber(extraitNaissance.getExtraitNumber())
                .createdAt(extraitNaissance.getCreatedAt())
                .dateEmission(extraitNaissance.getDateEmission())
                .idPerson(extraitNaissance.getPerson().getIdPerson())
                .slug(extraitNaissance.getSlug())
                .slugOrganisme(extraitNaissance.getSlugOrganisme())
                .build();
    }

    @Override
    public ExtraitNaissance toEntity(ExtraitNaissanceDto dto) {
        Person person = personService.findUniqueById(dto.getIdPerson());
        DeclarationNaissance declaration = declarationService.findUniqueById(dto.getIdDeclarationNaissance());

        if (dto.getSlug() != null){
            ExtraitNaissance extraitNaissance = extraitNaissanceService
                    .findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto,person, extraitNaissance, declaration);
        }else{
            Sequence_table sequenceTable = sequenceTableService.find("extrait");

            sequenceTableService.update(sequenceTable);

            dto.setExtraitNumber(sequenceTable.getNext_value());
            dto.setCodeUnique(person.getCodeUnique());
            dto.setSlugOrganisme(declaration.getSlugOrganisme());

            return dto.create(dto, person, declaration);
        }
    }
}
