package com.sayan.code.onlinevotingsystem.Controller;


import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Service.Implementation.CandidateServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/online-election/candidate")
public class CandidateControllers {

    @Autowired
    private CandidateServicesImpl candidateServices;

    @PostMapping("/add/{party_id}/{cons_id}/{elec_id}")
    public ResponseEntity<String> addCandidate(@RequestBody Candidate candidate, @PathVariable String party_id, @PathVariable String cons_id, @PathVariable String elec_id) {
        String addCandidate = candidateServices.addCandidate(candidate, party_id, cons_id, elec_id);
        return new ResponseEntity<>(addCandidate, HttpStatus.OK);
    }

    @GetMapping("/view/{cand_id}")
    public ResponseEntity<Candidate> viewCandidate(@PathVariable String cand_id) {
        Candidate candidate = candidateServices.getCandidate(cand_id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

}
