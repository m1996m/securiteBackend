package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.DocumentDemande;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDemandeDAO extends GenericDAO<DocumentDemande> {
    public DocumentDemandeDAO() {
        super(DocumentDemande.class);
    }
}
