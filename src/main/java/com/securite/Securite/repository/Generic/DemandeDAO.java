package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.Demande;
import org.springframework.stereotype.Repository;

@Repository
public class DemandeDAO extends GenericDAO<Demande> {
    public DemandeDAO() {
        super(Demande.class);
    }
}
