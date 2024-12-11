package com.securite.Securite.dto.carteIdentite;

import com.securite.Securite.model.CarteIdentite;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.Person;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarteIdentiteDto {
    private long idCardIdentity;
    private Date delivranceDate;
    private Date expirationDate;
    private long cardNumber;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private long idPerson;
    private String codeUnique;

    public CarteIdentite create(CarteIdentiteDto dto, Organisme organisme, Person person){
        CarteIdentite carteIdentite = new CarteIdentite();
        carteIdentite.setDelivranceDate(dto.getDelivranceDate());
        carteIdentite.setExpirationDate(dto.getExpirationDate());
        carteIdentite.setCardNumber(dto.getCardNumber());
        carteIdentite.setSlugOrganisme(dto.getSlugOrganisme());
        carteIdentite.setOrganisme(organisme);
        carteIdentite.setPerson(person);
        carteIdentite.setCodeUnique(person.getCodeUnique());

       return carteIdentite;
   }

    public CarteIdentite update(CarteIdentiteDto dto, Organisme organisme, Person person, CarteIdentite carteIdentite){
        carteIdentite.setDelivranceDate(dto.getDelivranceDate());
        carteIdentite.setExpirationDate(dto.getExpirationDate());
        carteIdentite.setCardNumber(dto.getCardNumber());
        carteIdentite.setSlugOrganisme(dto.getSlugOrganisme());
        carteIdentite.setOrganisme(organisme);
        carteIdentite.setPerson(person);

        return carteIdentite;
    }
}
