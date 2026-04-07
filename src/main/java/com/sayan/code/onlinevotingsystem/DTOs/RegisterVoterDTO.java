package com.sayan.code.onlinevotingsystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterVoterDTO {
    private String voter_name;
    private String voter_dob;
    private String phone_number;
    private String voter_address;
    private String constituency_id;
}
