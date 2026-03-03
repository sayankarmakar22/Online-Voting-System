package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Entity.VoteResult;
import com.sayan.code.onlinevotingsystem.Repository.ElectionRepo;
import com.sayan.code.onlinevotingsystem.Service.ElectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ElectionServiceImpl implements ElectionServices {
    @Autowired
    private ElectionRepo electionRepo;


    @Override
    public boolean addElection(Election election) {
        election.setElection_id(UUID.randomUUID().toString().substring(0, 8));
        election.setCreated_at(new Date());
        electionRepo.save(election);
            return true;
    }

    @Override
    public Election getElection(String id) {
        if(electionRepo.existsById(id)){
            return electionRepo.findById(id).get();
        }
        return null;
    }

    @Override
    public String closeElection(String id) {
        int closed = electionRepo.closeElection(id, "CLOSED");
        if(closed >= 1){
            return "CLOSED";
        }
        return "not closed";
    }

    @Override
    public Election updateElection(Election election) {
        if(electionRepo.existsById(election.getElection_id())){
            election.setCreated_at(new Date());
            return electionRepo.save(election);
        }


        return null;
    }

    @Override
    public boolean publishElection(String id) {
        int rows = electionRepo.publishElection(id,"DATE_PUBLISHED");
        if(rows >= 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean generateResult(String cons_id, String admin_id) {
        return false;
    }

    @Override
    public boolean publishResult(String cons_id, String admin_id) {
        return false;
    }
}
