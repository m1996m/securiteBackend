package com.securite.Securite.service;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Election;
import com.securite.Securite.repository.Generic.ElectionDAO;
import org.springframework.stereotype.Service;

@Service
public class ElectionService extends GenericService<Election> {
    public ElectionService(ElectionDAO electionDAO) {
        super(electionDAO);
    }
}
