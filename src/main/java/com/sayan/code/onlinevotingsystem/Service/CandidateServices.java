package com.sayan.code.onlinevotingsystem.Service;

import com.sayan.code.onlinevotingsystem.Entity.Candidate;

public interface CandidateServices {
    String addCandidate(Candidate candidate,String party_id,String cons_id,String elec_id);
    Candidate getCandidate(String id);
}
