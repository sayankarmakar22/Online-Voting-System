package com.sayan.code.onlinevotingsystem.Service;

import com.sayan.code.onlinevotingsystem.Entity.Party;

public interface PartyServices {
    String addParty(Party party);
    String removeParty(String id);
    String updateParty(Party party);
    Party getParty(String id);
}
