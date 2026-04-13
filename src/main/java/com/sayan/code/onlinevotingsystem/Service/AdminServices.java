package com.sayan.code.onlinevotingsystem.Service;

import com.sayan.code.onlinevotingsystem.DTOs.DTOAdmin;
import com.sayan.code.onlinevotingsystem.DTOs.DTOCandidate;
import com.sayan.code.onlinevotingsystem.Entity.Admin;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Voter;

import java.util.List;

public interface AdminServices {
    String register(Admin admin);
    boolean deletion(String id);
    DTOAdmin viewAdmin(String id,String type);
    boolean login(String id, String password);
    boolean logout(String id);
    List<DTOCandidate> getAllCandidatesByConstituency(String constituency_id);
    List<Voter> getAllVoterByConstituency(String constituency_id);
    List<DTOAdmin> getAllAdmins();
}
