package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.DeclarationNaissance;
import org.springframework.stereotype.Repository;

@Repository
public class DeclarationNaissanceDAO extends GenericDAO<DeclarationNaissance> {
    public DeclarationNaissanceDAO() {
        super(DeclarationNaissance.class);
    }
}
