package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Candidat;
import com.securite.Securite.repository.Generic.CandidatDAO;
import org.springframework.stereotype.Service;

@Service
public class CandidatService extends GenericService<Candidat> {
    public CandidatService(CandidatDAO candidatDAO) {
        super(candidatDAO);
    }
}
