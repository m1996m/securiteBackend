package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.DeclarationNaissance;
import com.securite.Securite.repository.Generic.DeclarationNaissanceDAO;
import org.springframework.stereotype.Service;

@Service
public class DeclarationNaissanceService extends GenericService<DeclarationNaissance> {
    public DeclarationNaissanceService(DeclarationNaissanceDAO declarationNaissanceDAO) {
        super(declarationNaissanceDAO);
    }
}
