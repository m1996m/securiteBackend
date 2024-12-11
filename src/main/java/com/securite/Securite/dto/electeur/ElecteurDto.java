package com.securite.Securite.dto.electeur;

import com.securite.Securite.model.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ElecteurDto {
    private long idElecteur;
    private Date dateInscription;
    private boolean statutIscription;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idPerson;
    private String codeUnique;

    public Electeur create(ElecteurDto electeurDto, Person person){
        Electeur electeur = new Electeur();
        electeur.setDateInscription(electeurDto.getDateInscription());
        electeur.setStatutIscription(electeurDto.isStatutIscription());
        electeur.setPerson(person);
        electeur.setSlugOrganisme(electeurDto.getSlugOrganisme());
        electeur.setCodeUnique(person.getCodeUnique());

       return electeur;
   }

    public Electeur update(ElecteurDto electeurDto, Person person, Electeur electeur){
        electeur.setDateInscription(electeurDto.getDateInscription());
        electeur.setStatutIscription(electeurDto.statutIscription);
        electeur.setPerson(person);

        return electeur;
    }
}
