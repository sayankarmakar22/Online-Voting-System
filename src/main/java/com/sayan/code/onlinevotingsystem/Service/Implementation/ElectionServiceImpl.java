package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.DTOs.DTOCandidate;
import com.sayan.code.onlinevotingsystem.DTOs.DTOElection;
import com.sayan.code.onlinevotingsystem.ENUMS.Status;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Entity.VoteResult;
import com.sayan.code.onlinevotingsystem.Entity.VoteStatus;
import com.sayan.code.onlinevotingsystem.Helper.ConvertToDTOCandidate;
import com.sayan.code.onlinevotingsystem.Repository.ElectionRepo;
import com.sayan.code.onlinevotingsystem.Service.ElectionServices;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ElectionServiceImpl implements ElectionServices {
    @Autowired
    private ElectionRepo electionRepo;


    @Override
    public String addElection(DTOElection dtoElection) {
        Election election = new Election();

        election.setElection_id(UUID.randomUUID().toString().substring(0, 8));
        election.setCreated_at(new Date());
        election.setEnd_date(dtoElection.getEnd_date());
        election.setStart_date(dtoElection.getStart_date());
        election.setStatus(Status.DATE_PUBLISHED);
        election.setType(dtoElection.getType());

        return electionRepo.save(election).getElection_id();
    }

    @Override
    public Election getElection(String id) {
        if(electionRepo.existsById(id)){
            return electionRepo.findById(id).get();
        }
        return null;
    }

    @Override
    @Transactional
    public String modifyStatus(String id, Status voteStatus) {
        Election election = electionRepo.findById(id).orElseThrow();
        election.setStatus(voteStatus);
        Election updated = electionRepo.save(election);

        if(id.equals(updated.getElection_id())){
            return "Modified The Election Status To : " + voteStatus;
        }
        return "Modification Failed";
    }



}
