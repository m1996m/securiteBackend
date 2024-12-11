package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Electeur;
import com.securite.Securite.repository.Generic.ElecteurDAO;
import org.springframework.stereotype.Service;

@Service
public class ElecteurService extends GenericService<Electeur> {
    public ElecteurService(ElecteurDAO electeurDAO) {
        super(electeurDAO);
    }
}
