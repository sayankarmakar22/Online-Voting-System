package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.Service.UserServices;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {
    @Override
    public HttpStatusCode login(String epic_num, String dob) {
        return null;
    }

    @Override
    public HttpStatusCode logout(String epic_num) {
        return null;
    }

    @Override
    public List<Object> viewElectionDetails(String constituency_id) {
        return List.of();
    }

    @Override
    public List<Object> viewCandidateList(String constituency_id) {
        return List.of();
    }

    @Override
    public Object viewProfile(String epic_num) {
        return null;
    }

    @Override
    public HttpStatusCode castVote(String epic_num) {
        return null;
    }

    @Override
    public List<Object> userElectionHistory(String epic_num) {
        return List.of();
    }

    @Override
    public List<Object> ViewResult(String constituency_id) {
        return List.of();
    }

    @Override
    public String userPhoneNumber(String epic_num) {
        return "";
    }
}
