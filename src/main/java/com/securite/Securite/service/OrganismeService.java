package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.repository.Generic.OrganismeDAO;
import org.springframework.stereotype.Service;

@Service
public class OrganismeService extends GenericService<Organisme> {
    public OrganismeService(OrganismeDAO organismeDAO) {
        super(organismeDAO);
    }
}
