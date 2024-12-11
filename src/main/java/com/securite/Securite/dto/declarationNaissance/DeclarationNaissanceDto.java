package com.securite.Securite.dto.declarationNaissance;

import com.securite.Securite.enumeration.Statut;
import com.securite.Securite.model.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeclarationNaissanceDto {
    private long idDeclarationBirth;
    private LocalDateTime declartionDate;
    private String place;
    private Statut statut = Statut.ATTENTE;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private long idPere;
    private long idMere;
    private long idEnfant;
    private long idDeclarant;
    private String rangNaissance;
    private String typeNaissance;
    private String codeUnique;
    private String lienDeclarant;

    public DeclarationNaissance create(
            DeclarationNaissanceDto dto,
            Organisme organisme,
            Person pere,
            Person mere,
            Person enfant,
            Person declarant
    ){
        DeclarationNaissance declaration = new DeclarationNaissance();
        declaration.setDeclartionDate(dto.getDeclartionDate());
        declaration.setPlace(dto.getPlace());
        declaration.setStatut(dto.getStatut());
        declaration.setRangNaissance(dto.getRangNaissance());
        declaration.setTypeNaissance(dto.getTypeNaissance());
        declaration.setSlugOrganisme(dto.getSlugOrganisme());
        declaration.setLienDeclarant(dto.getLienDeclarant());
        declaration.setOrganisme(organisme);
        declaration.setPere(pere);
        declaration.setMere(mere);
        declaration.setEnfant(enfant);
        declaration.setDeclarant(declarant);
        declaration.setCodeUnique(enfant.getCodeUnique());

       return declaration;
   }

    public DeclarationNaissance update(
            DeclarationNaissanceDto dto,
            Organisme organisme,
            Person pere,
            Person mere,
            Person enfant,
            Person declarant,
            DeclarationNaissance declaration
    ){
        declaration.setDeclartionDate(dto.getDeclartionDate());
        declaration.setPlace(dto.getPlace());
        declaration.setStatut(dto.getStatut());
        declaration.setSlugOrganisme(dto.getSlugOrganisme());
        declaration.setOrganisme(organisme);
        declaration.setPere(pere);
        declaration.setMere(mere);
        declaration.setEnfant(enfant);
        declaration.setDeclarant(declarant);
        declaration.setRangNaissance(dto.getRangNaissance());
        declaration.setTypeNaissance(dto.getTypeNaissance());
        declaration.setLienDeclarant(dto.getLienDeclarant());

        return declaration;
    }
}
