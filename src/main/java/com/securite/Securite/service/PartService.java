package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.repository.Generic.OrganismeDAO;
import org.springframework.stereotype.Service;

@Service
public class PartService extends GenericService<Organisme> {
    public PartService(OrganismeDAO organismeDAO) {
        super(organismeDAO);
    }
}
