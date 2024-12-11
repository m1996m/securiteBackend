package com.securite.Securite.dto.person;

import com.securite.Securite.enumeration.Genre;
import com.securite.Securite.model.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDto {
    private long idPerson;
    private String firtName;
    private String lastName;
    private String address;
    private String placeOfBirth;
    private String tel;
    private String slug;
    private String image;
    private Date dateOfBirth;
    private String email;
    private String nationality;
    private String codeUnique;
    private String region;
    private String secteur;
    private LocalDateTime createdAt;
    private Genre genre = Genre.HOMME;
    private String profession;

    public Person create(PersonDto dto){
        Person person = new Person();
        person.setFirtName(dto.getFirtName());
        person.setLastName(dto.getLastName());
        person.setEmail(dto.getEmail());
        person.setAddress(dto.getAddress());
        person.setRegion(dto.getRegion());
        person.setSecteur(dto.getSecteur());
        person.setCodeUnique(dto.getCodeUnique());
        person.setTel(dto.getTel());
        person.setNationality(dto.getNationality());
        person.setPlaceOfBirth(dto.getPlaceOfBirth());
        person.setDateOfBirth(dto.getDateOfBirth());
        person.setGenre(dto.getGenre());
        person.setProfession(dto.getProfession());

        return person;
   }

    public Person update(PersonDto dto, Person person){
        person.setFirtName(dto.getFirtName());
        person.setLastName(dto.getLastName());
        person.setEmail(dto.getEmail());
        person.setAddress(dto.getAddress());
        person.setCodeUnique(dto.getCodeUnique());
        person.setImage(dto.getImage());
        //person.setSlugOrganisme(dto.getSlugOrganisme());
        person.setNationality(dto.getNationality());
        person.setPlaceOfBirth(dto.getPlaceOfBirth());
        person.setDateOfBirth(dto.getDateOfBirth());

        return person;
    }
}
