package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.CarteElectorale;
import org.springframework.stereotype.Repository;

@Repository
public class CarteElectoraleDAO extends GenericDAO<CarteElectorale> {
    public CarteElectoraleDAO() {
        super(CarteElectorale.class);
    }
}
