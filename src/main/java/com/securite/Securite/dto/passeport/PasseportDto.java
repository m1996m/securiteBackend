package com.securite.Securite.dto.passeport;

import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.Passeport;
import com.securite.Securite.model.Person;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasseportDto {
    private long idPasseport;
    private Date delivranceDate;
    private Date expirationDate;
    private String passportNumber;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private long idPerson;
    private String codeUnique;

    public Passeport create(PasseportDto dto, Organisme organisme, Person person){
        Passeport passeport = new Passeport();
        passeport.setDelivranceDate(dto.getDelivranceDate());
        passeport.setExpirationDate(dto.getExpirationDate());
        passeport.setPassportNumber(dto.getPassportNumber());
        passeport.setSlugOrganisme(dto.getSlugOrganisme());
        passeport.setOrganisme(organisme);
        passeport.setPerson(person);
        passeport.setCodeUnique(person.getCodeUnique());

       return passeport;
   }

    public Passeport update(PasseportDto dto, Organisme organisme, Person person, Passeport passeport){
        passeport.setDelivranceDate(dto.getDelivranceDate());
        passeport.setExpirationDate(dto.getExpirationDate());
        passeport.setPassportNumber(dto.getPassportNumber());
        passeport.setSlugOrganisme(dto.getSlugOrganisme());
        passeport.setOrganisme(organisme);
        passeport.setPerson(person);

        return passeport;
    }
}
