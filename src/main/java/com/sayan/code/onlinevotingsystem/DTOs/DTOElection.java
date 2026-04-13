package com.sayan.code.onlinevotingsystem.DTOs;

import com.sayan.code.onlinevotingsystem.ENUMS.VoteType;
import lombok.Data;

import java.util.Date;

@Data
public class DTOElection {

    private VoteType type;
    private Date start_date;
    private Date end_date;
}
