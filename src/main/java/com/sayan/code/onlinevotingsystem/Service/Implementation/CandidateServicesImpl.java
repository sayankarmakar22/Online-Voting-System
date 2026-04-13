package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.DTOs.DTOCandidate;
import com.sayan.code.onlinevotingsystem.DTOs.RegisterCandidateDTO;
import com.sayan.code.onlinevotingsystem.ENUMS.ActiveStatus;
import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.VoteResult;
import com.sayan.code.onlinevotingsystem.Helper.ConvertToDTOCandidate;
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
    public String addCandidate(RegisterCandidateDTO registerCandidateDTO) {
        Candidate candidate1 = new Candidate(
                UUID.randomUUID().toString().substring(0,5),
                registerCandidateDTO.getName(),
                registerCandidateDTO.getAddress(),
                registerCandidateDTO.getDob(),
                Role.CANDIDATE,
                registerCandidateDTO.getPhn_num(),
                partyRepo.findById(registerCandidateDTO.getParty_id()),
                ActiveStatus.TRUE,
                constituencyRepo.findById(registerCandidateDTO.getCons_id()),
                electionRepo.findById(registerCandidateDTO.getElec_id())
        );
        return candidateRepo.save(candidate1).getCand_id();
    }


    @Override
    public DTOCandidate getCandidate(String id) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        return ConvertToDTOCandidate.convert(candidate);
    }
}
