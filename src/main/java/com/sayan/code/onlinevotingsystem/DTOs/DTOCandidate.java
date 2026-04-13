package com.sayan.code.onlinevotingsystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOCandidate {
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
}
