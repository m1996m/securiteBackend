package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Demande;
import com.securite.Securite.repository.Generic.DemandeDAO;
import org.springframework.stereotype.Service;

@Service
public class DemandeService extends GenericService<Demande> {
    public DemandeService(DemandeDAO demandeDAO) {
        super(demandeDAO);
    }
}
