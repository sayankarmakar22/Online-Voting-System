package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Entity.Voter;
import com.sayan.code.onlinevotingsystem.Repository.*;
import com.sayan.code.onlinevotingsystem.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private VoterRepo voterRepo;

    @Autowired
    private VoteRepo voteRepo;

    @Autowired
    private CandidateRepo candidateRepo;

    @Autowired
    private ElectionRepo electionRepo;

    @Override
    public boolean login(String epic_num, String dob) {
        Voter voter = voterRepo.findById(epic_num).orElseThrow();
        return voter.getDob().equals(dob);
    }

    @Override
    public boolean logout(String epic_num) {
        Voter voter = voterRepo.findById(epic_num).orElseThrow();
        return true;

    }

    @Override
    public Election viewElectionDetails(String election_id) {
        return electionRepo.findById(election_id).orElseThrow();
    }

    @Override
    public List<Candidate> viewCandidateList(String constituency_id) {
        return candidateRepo.findByConstituency_id(constituency_id);
    }

    @Override
    public Object viewProfile(String epic_num) {
        return voterRepo.findById(epic_num);
    }

    @Override
    public boolean castVote(String epic_num) {
        return false;
    }

    @Override
    public Object userElectionHistory(String vote_id) {
        return voteRepo.findById(vote_id);
    }

    @Override
    public List<Object> ViewResult(String constituency_id) {
        return List.of();
    }

    @Override
    public String userPhoneNumber(String epic_num) {
        return voterRepo.findById(epic_num).get().getPhone_number();
    }
}
