package com.securite.Securite.service;

import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Vote;
import com.securite.Securite.repository.Generic.VoteDAO;
import org.springframework.stereotype.Service;

@Service
public class VoteService extends GenericService<Vote> {
    public VoteService(VoteDAO voteDAO) {
        super(voteDAO);
    }
}
