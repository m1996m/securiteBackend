package com.securite.Securite.dto.person;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.Person;
import com.securite.Securite.model.Sequence_table;
import com.securite.Securite.service.PersonService;
import com.securite.Securite.service.Sequence_tableService;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoMapper implements GenericDtoMapper<PersonDto, Person> {
    private final PersonService personService;
    private final Sequence_tableService service;

    public PersonDtoMapper(PersonService personService, Sequence_tableService service) {
        this.personService = personService;
        this.service = service;
    }

    @Override
    public PersonDto toDto(Person person) {
        return PersonDto.builder()
                .firtName(person.getFirtName())
                .lastName((person.getLastName()))
                .address(person.getAddress())
                .idPerson(person.getIdPerson())
                .codeUnique(person.getCodeUnique())
                .nationality(person.getNationality())
                .email(person.getEmail())
                .image((person.getImage()))
                .slug(person.getSlug())
                .tel(person.getTel())
                .secteur(person.getSecteur())
                .region(person.getRegion())
                .placeOfBirth(person.getPlaceOfBirth())
                .dateOfBirth(person.getDateOfBirth())
                .build();
    }

    @Override
    public Person toEntity(PersonDto personDto) {
        if (personDto.getSlug() != null){
            Person person = personService.findUniqueWithValueAndAttribut(personDto.getSlug(), "slug");

            return personDto.update(personDto, person);
        }else {
            Sequence_table sequenceTable = service.find("person");

            personDto.setCodeUnique(String.valueOf(sequenceTable.getNext_value()));

            service.update(sequenceTable);

            return personDto.create(personDto);
        }
    }
}
