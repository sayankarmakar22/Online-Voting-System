package com.sayan.code.onlinevotingsystem.Controller;

import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Repository.ElectionRepo;
import com.sayan.code.onlinevotingsystem.Service.Implementation.ElectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/online-election/election")
public class ElectionControllers {
    @Autowired
    private ElectionServiceImpl electionService;
    @Autowired
    private ElectionRepo electionRepo;
    @Autowired
    private ElectionServiceImpl electionServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<String> createElection(@RequestBody Election election) {
        String addElection = electionServiceImpl.addElection(election);
        return new ResponseEntity<>(addElection, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<Election> editElection(@RequestBody Election election) {
        try{
            electionService.updateElection(election);
            return new ResponseEntity<>(election, HttpStatus.OK);
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
    @PutMapping("/publish/{elec_id}")
    public ResponseEntity<String> publishElection(@PathVariable String elec_id) {
        if(electionService.publishElection(elec_id)) {
            return new ResponseEntity<>("Published",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/close/{id}")
    public ResponseEntity<String> closeElection(@PathVariable String id) {
        try{
            return new ResponseEntity<>(electionService.closeElection(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    /*
    @PostMapping("/generate/result/{cons_id}/{admin_id}")
    public ResponseEntity<String> generateResult(@PathVariable String cons_id, @PathVariable String admin_id) {
        try{
            electionService.generateResult(cons_id, admin_id);
            return new ResponseEntity<>("Generated result", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/publish/result/{cons_id}/{admin_id}")
    public ResponseEntity<String> publishResult(@PathVariable String cons_id, @PathVariable String admin_id) {
        try{
            electionService.publishResult(cons_id,admin_id);
            return new ResponseEntity<>("Published", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
     */
}
