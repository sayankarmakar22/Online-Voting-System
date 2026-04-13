package com.sayan.code.onlinevotingsystem.DTOs;

import com.sayan.code.onlinevotingsystem.ENUMS.Status;
import lombok.Data;

@Data
public class DTOModifyElection {
    String elec_id;
    Status status;
}
