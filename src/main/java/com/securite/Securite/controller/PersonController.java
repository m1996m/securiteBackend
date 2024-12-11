package com.securite.Securite.controller;

import com.securite.Securite.dto.person.PersonDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Person;
import com.securite.Securite.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController extends GenericController<Person, PersonDto> {
    private final PersonService personService;

    public PersonController(
            GenericService<Person> genericService,
            GenericDtoMapper<PersonDto, Person> genericDtoMapper,
            PersonService personService
    ) {
        super(genericService, genericDtoMapper);
        this.personService = personService;
    }

    @Operation(summary = "Get person by codeUnique")
    @GetMapping("/code/unique")
    public Person getPersonByCodeUnique(@RequestParam("codeUnique") String codeUnique){
        return personService.findUniqueWithValueAndAttribut(codeUnique, "codeUnique");
    }

    @Operation(summary = "Get person with By tel")
    @GetMapping("/tel")
    public Person getPersonByTel(@RequestParam("tel") String tel){
        return personService.findUniqueWithValueAndAttribut(tel,"tel");
    }

    @Operation(summary = "Get person with By ville")
    @GetMapping("/placeOfBirth")
    public List<Person> getPersonByTel(
            @RequestParam("placeOfBirth") String placeOfBirth,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return personService.findAllByAttributeNameAndValue(placeOfBirth,"placeOfBirth", page, size);
    }

    @Operation(summary = "Get person with By dateBirth and nationality")
    @GetMapping("/nationality/age")
    public List<Person> getPersonByAgeAndNationality(
            @RequestParam("placeOfBirth") String placeOfBirth,
            @RequestParam("nationality") String nationality,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return personService.findAllByAgeAndNationality(18, nationality, placeOfBirth, page, size);
    }

    @Operation(summary = "Get person with By dateBirth and nationality")
    @GetMapping("/nationality/age/count")
    public Long getPersonByAgeAndNationality(@RequestParam("nationality") String nationality){
        return personService.countByAgeAndNationality(0, nationality);
    }
}
