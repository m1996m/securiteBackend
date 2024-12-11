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
public class DocumentDemandeDto {
    private long idDocumentDemande;
    private String type;
    private String slug;
    private String image;
    private LocalDateTime createdAt;
    private long idDemande;
    private String slugOrganisme;

    public DocumentDemande create(DocumentDemandeDto dto, Demande demande){
        DocumentDemande document = new DocumentDemande();
        document.setImage(dto.getImage());
        document.setSlugOrganisme(dto.getSlugOrganisme());
        document.setType(dto.getType());
        document.setDemande(demande);

       return document;
   }

    public DocumentDemande update(DocumentDemandeDto dto, Demande demande, DocumentDemande document){
        document.setImage(dto.getImage());
        document.setSlugOrganisme(dto.getSlugOrganisme());
        document.setType(dto.getType());
        document.setDemande(demande);

        return document;
    }
}
