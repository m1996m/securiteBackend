package com.securite.Securite.controller;

import com.securite.Securite.dto.documentDemande.DocumentDemandeDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.DocumentDemande;
import com.securite.Securite.service.DocumentDemandeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demande/document")
public class DocumentDemandeController extends GenericController<DocumentDemande, DocumentDemandeDto> {
    public DocumentDemandeController(
            GenericService<DocumentDemande> genericService,
            GenericDtoMapper<DocumentDemandeDto, DocumentDemande> genericDtoMapper
    ) {
        super(genericService, genericDtoMapper);
    }


}
