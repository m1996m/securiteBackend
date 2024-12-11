package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.CarteIdentite;
import org.springframework.stereotype.Repository;

@Repository
public class CarteIdentiteDAO extends GenericDAO<CarteIdentite> {
    public CarteIdentiteDAO() {
        super(CarteIdentite.class);
    }
}
