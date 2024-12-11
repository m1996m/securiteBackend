package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.Candidat;
import com.securite.Securite.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAO extends GenericDAO<Person> {
    public PersonDAO() {
        super(Person.class);
    }
}
