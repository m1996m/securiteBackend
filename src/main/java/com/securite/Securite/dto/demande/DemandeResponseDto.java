package com.securite.Securite.dto.demande;

import com.securite.Securite.dto.documentDemande.DocumentDemandeDto;
import com.securite.Securite.enumeration.Statut;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DemandeResponseDto {
    private long idDemande;
    private Date dateDemande;
    private Statut statut = Statut.ATTENTE;
    private Statut typeDocument;
    private Date dateValidation;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idPerson;
    private List<DocumentDemandeDto> documentDemandes;
    private String codeUnique;
}
