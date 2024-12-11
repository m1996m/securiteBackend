package com.securite.Securite.dto.declarationNaissance;

import com.securite.Securite.dto.extraitNaissance.ExtraitNaissanceDto;
import com.securite.Securite.enumeration.Statut;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeclarationNaissanceResponseDto {
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
    private List<ExtraitNaissanceDto> extraitNaissances;
    private String codeUnique;
}
