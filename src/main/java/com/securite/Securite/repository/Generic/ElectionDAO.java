package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.Election;
import org.springframework.stereotype.Repository;

@Repository
public class ElectionDAO extends GenericDAO<Election> {
    public ElectionDAO() {
        super(Election.class);
    }
}
