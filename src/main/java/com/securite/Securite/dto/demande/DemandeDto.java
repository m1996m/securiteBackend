package com.securite.Securite.dto.demande;

import com.securite.Securite.enumeration.Statut;
import com.securite.Securite.model.Demande;
import com.securite.Securite.model.Person;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DemandeDto {
    private long idDemande;
    private Date dateDemande;
    private Statut statut = Statut.ATTENTE;
    private Statut typeDocument;
    private Date dateValidation;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idPerson;
    private String codeUnique;
    private long numeroDemande;

    public Demande create(DemandeDto dto, Person person){
        Demande demande = new Demande();
        demande.setDateDemande(dto.getDateDemande());
        demande.setTypeDocument(dto.getTypeDocument());
        demande.setDateValidation(dto.getDateValidation());
        demande.setPerson(person);
        demande.setNumeroDemande(dto.getNumeroDemande());
        demande.setSlugOrganisme(dto.getSlugOrganisme());
        demande.setCodeUnique(person.getCodeUnique());

        return demande;
   }

    public Demande update(DemandeDto dto, Person person, Demande demande){
        demande.setDateDemande(dto.getDateDemande());
        demande.setTypeDocument(dto.getTypeDocument());
        demande.setDateValidation(dto.getDateValidation());
        demande.setPerson(person);
        demande.setSlugOrganisme(dto.getSlugOrganisme());

        return demande;
    }
}
