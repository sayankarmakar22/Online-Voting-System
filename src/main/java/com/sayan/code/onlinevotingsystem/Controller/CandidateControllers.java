package com.sayan.code.onlinevotingsystem.Controller;


import com.sayan.code.onlinevotingsystem.DTOs.DTOCandidate;
import com.sayan.code.onlinevotingsystem.DTOs.RegisterCandidateDTO;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Service.Implementation.CandidateServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/online-election/candidate")
public class CandidateControllers {

    @Autowired
    private CandidateServicesImpl candidateServices;

    @PostMapping("/add")
    public ResponseEntity<String> addCandidate(
            @RequestBody RegisterCandidateDTO registerCandidateDTO)
    {
        String addCandidate = candidateServices.addCandidate(registerCandidateDTO);
        return new ResponseEntity<>(addCandidate, HttpStatus.OK);
    }

    @GetMapping("/view/{cand_id}")
    public ResponseEntity<DTOCandidate> viewCandidate(
            @PathVariable String cand_id)
    {
        DTOCandidate candidate = candidateServices.getCandidate(cand_id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

}
