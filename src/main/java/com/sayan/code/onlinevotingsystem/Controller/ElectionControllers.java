package com.sayan.code.onlinevotingsystem.Controller;

import com.sayan.code.onlinevotingsystem.DTOs.DTOCandidate;
import com.sayan.code.onlinevotingsystem.DTOs.DTOElection;
import com.sayan.code.onlinevotingsystem.DTOs.DTOModifyElection;
import com.sayan.code.onlinevotingsystem.ENUMS.Status;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Entity.VoteStatus;
import com.sayan.code.onlinevotingsystem.Repository.ElectionRepo;
import com.sayan.code.onlinevotingsystem.Service.Implementation.ElectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/online-election/election")
public class ElectionControllers {
    @Autowired
    private ElectionServiceImpl electionService;
    @Autowired
    private ElectionRepo electionRepo;
    @Autowired
    private ElectionServiceImpl electionServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<String> createElection(@RequestBody DTOElection dtoElection) {
        String addElection = electionServiceImpl.addElection(dtoElection);
        return new ResponseEntity<>(addElection, HttpStatus.CREATED);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> editElection(
            @RequestBody DTOModifyElection dtoModifyElection
            ) {
        try{
            String modifyStatus = electionService.modifyStatus(dtoModifyElection.getElec_id(), dtoModifyElection.getStatus());
            return new ResponseEntity<>(modifyStatus, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/view/{elec_id}")
    public ResponseEntity<Election> viewElection(@PathVariable String elec_id) {
        try{
            Election election = electionService.getElection(elec_id);
            return new ResponseEntity<>(election, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
