package com.sayan.code.onlinevotingsystem.Service;

import com.sayan.code.onlinevotingsystem.Entity.Admin;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Voter;

import java.util.List;

public interface AdminServices {
    boolean register(Admin admin);
    boolean deletion(String id);
    Admin viewAdmin(String id);
    boolean login(String id, String password);
    boolean logout(String id);
    boolean approveCandidate(String id,String cand_id);
    boolean rejectCandidate(String id,String cand_id);
    List<Candidate> getAllCandidatesByConstituency(String constituency_id);
    List<Voter> getAllVoterByConstituency(String constituency_id);
    boolean suspendVoter(String epic_id,String admin_id,String reason);
}
