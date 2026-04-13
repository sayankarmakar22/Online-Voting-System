package com.sayan.code.onlinevotingsystem.Service;

import com.sayan.code.onlinevotingsystem.DTOs.DTOCandidate;
import com.sayan.code.onlinevotingsystem.DTOs.DTOElection;
import com.sayan.code.onlinevotingsystem.ENUMS.Status;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Entity.VoteResult;
import com.sayan.code.onlinevotingsystem.Entity.VoteStatus;

import java.util.List;

public interface ElectionServices {
    String addElection(DTOElection dtoElection);
    Election getElection(String id);
    String modifyStatus(String id, Status voteStatus);

}
