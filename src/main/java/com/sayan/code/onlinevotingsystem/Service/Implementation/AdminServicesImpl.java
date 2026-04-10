package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.DTOs.DTOAdmin;
import com.sayan.code.onlinevotingsystem.ENUMS.ActiveStatus;
import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import com.sayan.code.onlinevotingsystem.Entity.Admin;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Voter;
import com.sayan.code.onlinevotingsystem.Repository.AdminRepo;
import com.sayan.code.onlinevotingsystem.Repository.CandidateRepo;
import com.sayan.code.onlinevotingsystem.Repository.ConstituencyRepo;
import com.sayan.code.onlinevotingsystem.Repository.VoterRepo;
import com.sayan.code.onlinevotingsystem.Security.SecurityTools;
import com.sayan.code.onlinevotingsystem.Service.AdminServices;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
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
        log.info("Starting the registration process" );
        admin.setAdmin_id(UUID.randomUUID().toString().substring(0, 8));
        admin.setAdmin_password(SecurityTools.hashPassword(admin.getAdmin_password()));
        admin.setRole(Role.ADMIN);
        admin.setActive(ActiveStatus.TRUE);
        log.info("Hash of pass" + admin.getAdmin_password());
        log.info("Ending the registration process" +admin.getAdmin_id());
        return adminRepo.save(admin).getAdmin_id();
    }

    @Override
    public boolean deletion(String id) {
        try{
            Admin admin = adminRepo.findById(id).get();
            admin.setActive(ActiveStatus.FALSE);
            adminRepo.save(admin);
            log.info("Deleted admin with id: " + id);
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public DTOAdmin viewAdmin(String id) {
        Admin admin = adminRepo.findById(id).get();
        if(admin.getActive().equals(ActiveStatus.FALSE))
            return null;
        DTOAdmin admin1 = new DTOAdmin(
                admin.getAdmin_id(),
                admin.getAdmin_name(),
                admin.getAdmin_address(),
                admin.getAdmin_phone());
        return admin1;
    }

    @Override
    public boolean login(String id, String password) {
        Admin admin = adminRepo.findById(id).get();
        return SecurityTools.verifyPassword(password, admin.getAdmin_password());
    }

    @Override
    public boolean logout(String id) {
        if(adminRepo.existsById(id)){
            return true;
        }
        return false;
    }

    @Override
    public List<Candidate> getAllCandidatesByConstituency(String constituency_id) {
        log.info("Starting the getAllCandidatesByConstituency" + LocalDateTime.now());
        return candidateRepo.findByConstituency_id(constituency_id);
    }

    @Override
    public List<Voter> getAllVoterByConstituency(String constituency_id) {
        log.info("Starting the getAllVotesByConstituency" + LocalDateTime.now());
        return voterRepo.getAllVoterByConId(constituency_id);
    }

    @Override
    public List<DTOAdmin> getAllAdmins() {
        return adminRepo.findAll()
                .stream()
                .map(admin -> new DTOAdmin(
                        admin.getAdmin_id(),
                        admin.getAdmin_name(),
                        admin.getAdmin_address(),
                        admin.getAdmin_phone()
                ))
                .toList();
    }

}
