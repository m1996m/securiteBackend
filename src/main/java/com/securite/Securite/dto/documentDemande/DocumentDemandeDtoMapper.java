package com.securite.Securite.dto.documentDemande;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.Demande;
import com.securite.Securite.model.DocumentDemande;
import com.securite.Securite.service.DemandeService;
import com.securite.Securite.service.DocumentDemandeService;
import org.springframework.stereotype.Component;

@Component
public class DocumentDemandeDtoMapper implements
        GenericDtoMapper<DocumentDemandeDto, DocumentDemande> {
    private final DemandeService demandeService;
    private final DocumentDemandeService service;

    public DocumentDemandeDtoMapper(DemandeService demandeService, DocumentDemandeService service) {
        this.demandeService = demandeService;
        this.service = service;
    }

    @Override
    public DocumentDemandeDto toDto(DocumentDemande documentDemande) {
        return DocumentDemandeDto.builder()
                .image(documentDemande.getImage())
                .type(documentDemande.getType())
                .createdAt(documentDemande.getCreatedAt())
                .idDemande(documentDemande.getDemande().getIdDemande())
                .build();
    }

    @Override
    public DocumentDemande toEntity(DocumentDemandeDto dto) {
        Demande demande = demandeService.findUniqueById(dto.getIdDemande());

        if (dto.getSlug() != null) {
            DocumentDemande documentDemande = service
                    .findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, demande, documentDemande);
        }else {
            dto.setSlugOrganisme(demande.getSlugOrganisme());

            return dto.create(dto, demande);
        }
    }
}
