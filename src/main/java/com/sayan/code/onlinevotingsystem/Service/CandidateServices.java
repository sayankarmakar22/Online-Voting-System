package com.sayan.code.onlinevotingsystem.Service;

import com.sayan.code.onlinevotingsystem.DTOs.DTOCandidate;
import com.sayan.code.onlinevotingsystem.DTOs.RegisterCandidateDTO;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;

public interface CandidateServices {
    String addCandidate(RegisterCandidateDTO candidate);
    DTOCandidate getCandidate(String id);
}
