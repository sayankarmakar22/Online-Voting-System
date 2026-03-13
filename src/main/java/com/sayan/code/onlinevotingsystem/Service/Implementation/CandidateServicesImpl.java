package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Repository.CandidateRepo;
import com.sayan.code.onlinevotingsystem.Repository.ConstituencyRepo;
import com.sayan.code.onlinevotingsystem.Repository.ElectionRepo;
import com.sayan.code.onlinevotingsystem.Repository.PartyRepo;
import com.sayan.code.onlinevotingsystem.Service.CandidateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CandidateServicesImpl implements CandidateServices {
    @Autowired
    private CandidateRepo candidateRepo;
    @Autowired
    private PartyRepo partyRepo;
    @Autowired
    private ConstituencyRepo constituencyRepo;
    @Autowired
    private ElectionRepo electionRepo;

    @Override
    public String addCandidate(Candidate candidate,String party_id,String cons_id,String elec_id) {
        candidate.setCand_id(UUID.randomUUID().toString().substring(0, 5));
        candidate.setRole(Role.CANDIDATE);
        candidate.setParty(partyRepo.findById(party_id).get());
        candidate.setElection_id(electionRepo.findById(elec_id).get());
        candidate.setConstituency(constituencyRepo.findById(cons_id).get());
        return candidateRepo.save(candidate).getCand_id();
    }


    @Override
    public Candidate getCandidate(String id) {
        return candidateRepo.findById(id).orElse(null);
    }
}
