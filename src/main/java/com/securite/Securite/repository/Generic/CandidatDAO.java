package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.Candidat;
import org.springframework.stereotype.Repository;

@Repository
public class CandidatDAO extends GenericDAO<Candidat> {
    public CandidatDAO() {
        super(Candidat.class);
    }
}
