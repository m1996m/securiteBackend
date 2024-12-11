package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.Passeport;
import org.springframework.stereotype.Repository;

@Repository
public class PasseportDAO extends GenericDAO<Passeport> {
    public PasseportDAO() {
        super(Passeport.class);
    }
}
