package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.Entity.Party;
import com.sayan.code.onlinevotingsystem.Repository.PartyRepo;
import com.sayan.code.onlinevotingsystem.Service.PartyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PartyServicesImpl implements PartyServices {
    @Autowired
    private PartyRepo partyRepo;
    @Override
    public String addParty(Party party) {
        party.setParty_id(UUID.randomUUID().toString().substring(0, 4));
        return partyRepo.save(party).getParty_id();
    }

    @Override
    public String removeParty(String id) {
        partyRepo.deleteById(id);
        return "Removed Party";
    }

    @Override
    public String updateParty(Party party) {
        partyRepo.save(party);
        return "Updated Party";
    }

    @Override
    public Party getParty(String id) {
        return partyRepo.findById(id).orElse(null);
    }
}
