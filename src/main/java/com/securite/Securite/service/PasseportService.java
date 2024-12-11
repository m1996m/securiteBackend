package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Passeport;
import com.securite.Securite.repository.Generic.PasseportDAO;
import org.springframework.stereotype.Service;

@Service
public class PasseportService extends GenericService<Passeport> {
    public PasseportService(PasseportDAO passeportDAO) {
        super(passeportDAO);
    }
}
