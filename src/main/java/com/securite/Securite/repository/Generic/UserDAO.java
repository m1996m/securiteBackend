package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}
