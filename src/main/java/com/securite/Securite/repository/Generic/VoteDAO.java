package com.securite.Securite.repository.Generic;

import com.securite.Securite.generic.GenericDAO;
import com.securite.Securite.model.Vote;
import org.springframework.stereotype.Repository;

@Repository
public class VoteDAO extends GenericDAO<Vote> {
    public VoteDAO() {
        super(Vote.class);
    }
}
