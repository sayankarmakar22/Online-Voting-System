package com.sayan.code.onlinevotingsystem.Controller;

import com.sayan.code.onlinevotingsystem.Entity.Party;
import com.sayan.code.onlinevotingsystem.Service.Implementation.PartyServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/online-election/party")
public class PartyController {
    @Autowired
    private PartyServicesImpl partyServices;

    @PostMapping("/add")
    public ResponseEntity<String> addParty(@RequestBody Party party) {
        String addParty = partyServices.addParty(party);
        return new ResponseEntity<>(addParty, HttpStatus.CREATED);
    }
    @GetMapping("/view/{party_id}")
    public ResponseEntity<Party> viewParty(@PathVariable String party_id) {
        Party party = partyServices.getParty(party_id);
        return new ResponseEntity<>(party, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateParty(@RequestBody Party party) {
        partyServices.updateParty(party);
        return new ResponseEntity<>("Party updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{party_id}")
    public ResponseEntity<String> deleteParty(@PathVariable String party_id) {
        partyServices.removeParty(party_id);
        return new ResponseEntity<>("Party deleted successfully", HttpStatus.OK);
    }
}
