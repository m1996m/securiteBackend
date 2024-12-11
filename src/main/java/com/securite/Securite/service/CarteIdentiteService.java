package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.CarteIdentite;
import com.securite.Securite.repository.Generic.CarteIdentiteDAO;
import org.springframework.stereotype.Service;

@Service
public class CarteIdentiteService extends GenericService<CarteIdentite> {
    public CarteIdentiteService(CarteIdentiteDAO carteIdentiteDAO) {
        super(carteIdentiteDAO);
    }
}
