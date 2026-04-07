package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.DTOs.DTOVoter;
import com.sayan.code.onlinevotingsystem.DTOs.RegisterVoterDTO;
import com.sayan.code.onlinevotingsystem.ENUMS.ActiveStatus;
import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Entity.Voter;
import com.sayan.code.onlinevotingsystem.Repository.*;
import com.sayan.code.onlinevotingsystem.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


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
    @Autowired
    private ConstituencyRepo constituencyRepo;

    @Override
    public boolean login(String epic_num, String dob) {
        Voter voter = voterRepo.findById(epic_num).orElseThrow();
        return voter.getDob().equals(dob);
    }

    @Override
    public boolean logout(String epic_num) {
        return voterRepo.existsById(epic_num);

    }

    @Override
    public Election viewElectionDetails(String election_id) {
        return electionRepo.findById(election_id).orElseThrow();
    }

    @Override
    public List<Candidate> viewCandidateList(String epic_num) {
        Voter voter = voterRepo.findById(epic_num).orElseThrow();
        return candidateRepo.findByConstituency_id(voter.getConstituency().getConstituency_id());
    }

    @Override
    public DTOVoter viewProfile(String epic_num) {
        Voter voter = voterRepo.findById(epic_num).orElseThrow();
        if(voter.getActive().equals(ActiveStatus.FALSE)){
            return null;
        }
        return new DTOVoter(
                voter.getEpic_id(),
                voter.getName(),
                voter.getDob(),
                voter.getPhone_number(),
                voter.getAddress(),
                voter.getConstituency().getConstituency_name()
        );
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

    @Override
    public DTOVoter registerVoter(RegisterVoterDTO registerVoterDTO) {
        Voter voter = new Voter();
        voter.setEpic_id(UUID.randomUUID().toString().substring(0,8));
        voter.setName(registerVoterDTO.getVoter_name());
        voter.setDob(registerVoterDTO.getVoter_dob());
        voter.setPhone_number(registerVoterDTO.getPhone_number());
        voter.setActive(ActiveStatus.TRUE);
        voter.setAddress(registerVoterDTO.getVoter_address());
        voter.setRole(Role.VOTER);
        voter.setConstituency(constituencyRepo.findById(registerVoterDTO.getConstituency_id()).orElseThrow());

        Voter saved = voterRepo.save(voter);

        return new DTOVoter(
                saved.getEpic_id(),
                saved.getName(),
                saved.getDob(),
                saved.getPhone_number(),
                saved.getAddress(),
                saved.getConstituency().getConstituency_name()
        );
    }
}
