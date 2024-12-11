package com.securite.Securite.dto.documentDemande;

import com.securite.Securite.model.Demande;
import com.securite.Securite.model.DocumentDemande;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentDemandeResponseDto {
    private long idDocumentDemande;
    private String type;
    private String slug;
    private String image;
    private LocalDateTime createdAt;
    private long idDemande;
    private String slugOrganisme;
}
