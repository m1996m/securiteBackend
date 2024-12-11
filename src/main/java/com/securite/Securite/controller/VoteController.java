package com.securite.Securite.controller;

import com.securite.Securite.dto.vote.VoteDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Vote;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
public class VoteController extends GenericController<Vote, VoteDto> {
    public VoteController(
            GenericService<Vote> genericService,
            GenericDtoMapper<VoteDto, Vote> genericDtoMapper
    ) {
        super(genericService, genericDtoMapper);
    }
}
