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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserServicesImpl implements UserServices {

    private static final Logger log = LoggerFactory.getLogger(UserServicesImpl.class);
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

    @Autowired
    private VoterLoginDeviceServicesImpl voterLoginDeviceServices;

    @Override
    public boolean login(String epic_num, String dob) {
        if("Allowed".equals(voterLoginDeviceServices.setLoginTrue(epic_num))) {
            Voter voter = voterRepo.findById(epic_num).orElseThrow();
            return voter.getDob().equals(dob);
        }
        return false;
    }

    @Override
    public boolean logout(String epic_num) {
        if("logout".equals(voterLoginDeviceServices.setLoginFalse(epic_num))) {
            return voterRepo.existsById(epic_num);
        }
        return false;

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
    public DTOVoter viewProfile(String id) {
        Voter voter = voterRepo.findById(id).orElseThrow();

        if(voter.getActive().equals(ActiveStatus.FALSE)){
            return null;
        }
        return new DTOVoter(
                voter.getEpic_id(),
                voter.getName(),
                voter.getDob(),
                voter.getPhoneNumber(),
                voter.getAddress(),
                voter.getConstituency().getConstituency_name()
        );
    }

    @Override
    public DTOVoter viewProfileByPhone(String phn) {
        Voter voter = voterRepo.findByphoneNumber(phn);
        if(voter.getActive().equals(ActiveStatus.FALSE)){
            return null;
        }
        return new DTOVoter(
                voter.getEpic_id(),
                voter.getName(),
                voter.getDob(),
                voter.getPhoneNumber(),
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
        return voterRepo.findById(epic_num).get().getPhoneNumber();
    }

    @Override
    public DTOVoter registerVoter(RegisterVoterDTO registerVoterDTO) {
        Voter voter = new Voter();
        voter.setEpic_id(UUID.randomUUID().toString().substring(0,8));
        voter.setName(registerVoterDTO.getVoter_name());
        voter.setDob(registerVoterDTO.getVoter_dob());
        voter.setPhoneNumber(registerVoterDTO.getPhone_number());
        voter.setActive(ActiveStatus.TRUE);
        voter.setAddress(registerVoterDTO.getVoter_address());
        voter.setRole(Role.VOTER);
        voter.setConstituency(constituencyRepo.findById(registerVoterDTO.getConstituency_id()).orElseThrow());

        Voter saved = voterRepo.save(voter);

        return new DTOVoter(
                saved.getEpic_id(),
                saved.getName(),
                saved.getDob(),
                saved.getPhoneNumber(),
                saved.getAddress(),
                saved.getConstituency().getConstituency_name()
        );
    }
}
