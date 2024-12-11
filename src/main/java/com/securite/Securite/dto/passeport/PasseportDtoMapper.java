package com.securite.Securite.dto.passeport;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.Passeport;
import com.securite.Securite.model.Person;
import com.securite.Securite.model.Sequence_table;
import com.securite.Securite.service.OrganismeService;
import com.securite.Securite.service.PasseportService;
import com.securite.Securite.service.PersonService;
import com.securite.Securite.service.Sequence_tableService;
import org.springframework.stereotype.Component;

@Component
public class PasseportDtoMapper implements GenericDtoMapper<PasseportDto, Passeport> {
    private final PersonService personService;
    private final OrganismeService organismeService;
    private final PasseportService passeportService;
    private final Sequence_tableService sequenceTableService;

    public PasseportDtoMapper(
            PersonService personService,
            OrganismeService organismeService,
            PasseportService passeportService,
            Sequence_tableService sequenceTableService
    ) {
        this.personService = personService;
        this.organismeService = organismeService;
        this.passeportService = passeportService;
        this.sequenceTableService = sequenceTableService;
    }

    @Override
    public PasseportDto toDto(Passeport passeport) {
        return PasseportDto.builder()
                .idPasseport(passeport.getIdPasseport())
                .slug(passeport.getSlug())
                .passportNumber(passeport.getPassportNumber())
                .createdAt(passeport.getCreatedAt())
                .delivranceDate(passeport.getDelivranceDate())
                .expirationDate(passeport.getExpirationDate())
                .idOrganisme(passeport.getOrganisme().getIdOrganisme())
                .idPerson(passeport.getPerson().getIdPerson())
                .slugOrganisme(passeport.getSlugOrganisme())
                .build();
    }

    @Override
    public Passeport toEntity(PasseportDto passeportDto) {
        Person person = personService.findUniqueById(passeportDto.getIdPerson());
        Organisme organisme = organismeService.findUniqueById(passeportDto.getIdOrganisme());

        if (passeportDto.getSlug() != null){
            Passeport passeport = passeportService
                    .findUniqueWithValueAndAttribut(passeportDto.getSlug(),"slug");

            return passeportDto.update(passeportDto, organisme, person, passeport);
        }else {
            Sequence_table sequenceTable = sequenceTableService.find("passeport");

            sequenceTableService.update(sequenceTable);

            passeportDto.setPassportNumber(String.valueOf(sequenceTable.getNext_value()));
            passeportDto.setCodeUnique(person.getCodeUnique());
            passeportDto.setSlugOrganisme(organisme.getSlug());

            return passeportDto.create(passeportDto, organisme, person);
        }
    }
}
