package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.Electeur;
import org.springframework.stereotype.Repository;

@Repository
public class ElecteurDAO extends GenericDAO<Electeur> {
    public ElecteurDAO() {
        super(Electeur.class);
    }
}
