package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.ExtraitNaissance;
import com.securite.Securite.repository.Generic.ExtraitNaissanceDAO;
import org.springframework.stereotype.Service;

@Service
public class ExtraitNaissanceService extends GenericService<ExtraitNaissance> {
    public ExtraitNaissanceService(ExtraitNaissanceDAO extraitNaissanceDAO) {
        super(extraitNaissanceDAO);
    }
}
