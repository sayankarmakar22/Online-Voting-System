package com.sayan.code.onlinevotingsystem.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
//@Component
public class DTOVoter {
    private String epic_id;
    private String name;
    private String dob;
    private String phone_number;
    private String address;
    private String constituency_name;
}
