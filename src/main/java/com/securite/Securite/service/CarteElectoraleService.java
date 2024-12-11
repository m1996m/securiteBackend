package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.CarteElectorale;
import com.securite.Securite.repository.Generic.CarteElectoraleDAO;
import org.springframework.stereotype.Service;

@Service
public class CarteElectoraleService extends GenericService<CarteElectorale> {
    public CarteElectoraleService(CarteElectoraleDAO carteElectoraleDAO) {
        super(carteElectoraleDAO);
    }
}
