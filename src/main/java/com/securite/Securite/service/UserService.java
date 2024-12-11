package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.User;
import com.securite.Securite.repository.Generic.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User> {
    public UserService(UserDAO userDAO) {
        super(userDAO);
    }
}
