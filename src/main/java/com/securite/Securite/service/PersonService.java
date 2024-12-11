package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Person;
import com.securite.Securite.repository.Generic.PersonDAO;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends GenericService<Person> {
    public PersonService(PersonDAO personDAO) {
        super(personDAO);
    }
}
