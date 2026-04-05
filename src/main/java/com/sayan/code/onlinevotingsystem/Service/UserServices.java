package com.sayan.code.onlinevotingsystem.Service;

import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Election;

import java.util.List;

public interface UserServices {
    boolean login(String epic_num,String dob);
    boolean logout(String epic_num);
    Election viewElectionDetails(String election_id);
    List<Candidate> viewCandidateList(String constituency_id);
    Object viewProfile(String epic_num);
    boolean castVote(String epic_num);
    Object userElectionHistory(String vote_id);
    List<Object> ViewResult(String constituency_id);
    String userPhoneNumber(String epic_num);
}
