package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.Organisme;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class OrganismeDAO extends GenericDAO<Organisme> {
    public OrganismeDAO() {
        super(Organisme.class);
    }
}
