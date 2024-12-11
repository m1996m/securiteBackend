package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.Candidat;
import com.securite.Securite.model.ExtraitNaissance;
import org.springframework.stereotype.Repository;

@Repository
public class ExtraitNaissanceDAO extends GenericDAO<ExtraitNaissance> {
    public ExtraitNaissanceDAO() {
        super(ExtraitNaissance.class);
    }
}
