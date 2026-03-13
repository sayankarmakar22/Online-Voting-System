package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import com.sayan.code.onlinevotingsystem.Entity.Admin;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Voter;
import com.sayan.code.onlinevotingsystem.Repository.AdminRepo;
import com.sayan.code.onlinevotingsystem.Repository.CandidateRepo;
import com.sayan.code.onlinevotingsystem.Repository.ConstituencyRepo;
import com.sayan.code.onlinevotingsystem.Repository.VoterRepo;
import com.sayan.code.onlinevotingsystem.Service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServicesImpl implements AdminServices {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CandidateRepo candidateRepo;

    @Autowired
    private ConstituencyRepo constituencyRepo;


    @Autowired
    private VoterRepo voterRepo;
    @Override
    public String register(Admin admin) {
        admin.setAdmin_id(UUID.randomUUID().toString().substring(0, 8));
        admin.setRole(Role.ADMIN);
        return adminRepo.save(admin).getAdmin_id();
    }

    @Override
    public boolean deletion(String id) {
        if(!adminRepo.findById(id).isEmpty()){
            adminRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Admin viewAdmin(String id) {
        return adminRepo.findById(id).get();
    }

    @Override
    public boolean login(String id, String password) {
            if(password.equals(adminRepo.getPassword(id))){
                return true;
            }
            return false;
    }

    @Override
    public boolean logout(String id) {
        if(adminRepo.existsById(id)){
            return true;
        }
        return false;
    }

    @Override
    public boolean approveCandidate(String id, String cand_id) {
        return false;
    }

    @Override
    public boolean rejectCandidate(String id, String cand_id) {
        return false;
    }

    @Override
    public List<Candidate> getAllCandidatesByConstituency(String constituency_id) {
        return candidateRepo.findByConstituency_id(constituency_id);
    }

    @Override
    public List<Voter> getAllVoterByConstituency(String constituency_id) {
        return voterRepo.getAllVoterByConId(constituency_id);
    }

    @Override
    public boolean suspendVoter(String epic_id, String admin_id, String reason) {
        return false;
    }
}
