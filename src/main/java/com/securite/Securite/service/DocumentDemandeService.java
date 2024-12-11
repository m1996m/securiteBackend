package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.DocumentDemande;
import com.securite.Securite.repository.Generic.DocumentDemandeDAO;
import org.springframework.stereotype.Service;

@Service
public class DocumentDemandeService extends GenericService<DocumentDemande> {
    public DocumentDemandeService(DocumentDemandeDAO documentDemandeDAO) {
        super(documentDemandeDAO);
    }
}
