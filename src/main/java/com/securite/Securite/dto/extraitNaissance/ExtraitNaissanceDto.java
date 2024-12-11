package com.securite.Securite.dto.extraitNaissance;

import com.securite.Securite.model.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExtraitNaissanceDto {
    private long idExtraitNaissance;
    private Date dateEmission;
    private long extraitNumber;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idPerson;
    private String codeUnique;
    private long idDeclarationNaissance;
    private String lienDeclarant;

    public ExtraitNaissance create(
            ExtraitNaissanceDto dto,
            Person person,
            DeclarationNaissance declaration
    ){
        ExtraitNaissance extraitNaissance = new ExtraitNaissance();
        extraitNaissance.setDateEmission(dto.getDateEmission());
        extraitNaissance.setExtraitNumber(dto.getExtraitNumber());
        extraitNaissance.setSlugOrganisme(dto.getSlugOrganisme());
        extraitNaissance.setPerson(person);
        extraitNaissance.setDeclarationNaissance(declaration);
        extraitNaissance.setCodeUnique(person.getCodeUnique());
        extraitNaissance.setLienDeclarant(dto.getLienDeclarant());

       return extraitNaissance;
   }

    public ExtraitNaissance update(
            ExtraitNaissanceDto dto,
            Person person,
            ExtraitNaissance extraitNaissance,
            DeclarationNaissance declaration
    ){
        extraitNaissance.setDateEmission(dto.getDateEmission());
        extraitNaissance.setExtraitNumber(dto.getExtraitNumber());
        extraitNaissance.setSlugOrganisme(dto.getSlugOrganisme());
        extraitNaissance.setDeclarationNaissance(declaration);
        extraitNaissance.setPerson(person);
        extraitNaissance.setLienDeclarant(dto.getLienDeclarant());

        return extraitNaissance;
    }
}
