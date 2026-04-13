package com.sayan.code.onlinevotingsystem.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterCandidateDTO {
    private String name;
    private String address;
    private String dob;
    private String phn_num;
    private String party_id;
    private String cons_id;
    private String elec_id;
}
