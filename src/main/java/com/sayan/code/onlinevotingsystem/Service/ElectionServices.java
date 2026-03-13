package com.sayan.code.onlinevotingsystem.Service;

import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Entity.VoteResult;

public interface ElectionServices {
    String addElection(Election election);
    Election getElection(String id);
    String closeElection(String id);
    Election updateElection(Election election);
    boolean publishElection(String id);
    boolean generateResult(String cons_id, String admin_id);
    boolean publishResult(String cons_id, String admin_id);

}
