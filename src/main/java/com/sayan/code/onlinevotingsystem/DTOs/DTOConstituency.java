package com.sayan.code.onlinevotingsystem.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.PublicKey;

@AllArgsConstructor
@Data
public class DTOConstituency {
    private String id;
    private String name;
    private int total_num_of_candidates;
    private int total_num_of_voters;
}
