package com.sayan.code.onlinevotingsystem.Service;

import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface UserServices {
    HttpStatusCode login(String epic_num,String dob);
    HttpStatusCode logout(String epic_num);
    List<Object> viewElectionDetails(String constituency_id);
    List<Object> viewCandidateList(String constituency_id);
    Object viewProfile(String epic_num);
    HttpStatusCode castVote(String epic_num);
    List<Object> userElectionHistory(String epic_num);
    List<Object> ViewResult(String constituency_id);
    String userPhoneNumber(String epic_num);
}
