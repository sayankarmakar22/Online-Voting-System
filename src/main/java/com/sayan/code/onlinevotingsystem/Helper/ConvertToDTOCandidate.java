package com.sayan.code.onlinevotingsystem.Helper;

import com.sayan.code.onlinevotingsystem.DTOs.DTOCandidate;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;

public class ConvertToDTOCandidate {
    private String cand_id;
    private String cand_name;
    private String cand_address;
    private String cand_phone;
    private String cand_dob;
    private String party_name;
    private String constituency_id;
    private String constituency_name;
    private String election_type;
    private String election_date;

    public static DTOCandidate convert(Candidate candidate){
        return new DTOCandidate(
                candidate.getCand_id(),
                candidate.getName(),
                candidate.getAddress(),
                candidate.getPhoneNumber(),
                candidate.getDob(),
                candidate.getParty().getParty_name(),
                candidate.getConstituency().getConstituency_id(),
                candidate.getConstituency().getConstituency_name(),
                candidate.getElection_id().getType().toString(),
                candidate.getElection_id().getEnd_date().toString()

        );
    }

}
