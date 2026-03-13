package com.sayan.code.onlinevotingsystem.Controller;

import com.sayan.code.onlinevotingsystem.Entity.Admin;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Voter;
import com.sayan.code.onlinevotingsystem.Service.Implementation.AdminServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/v1/online-election/admin")
public class AdminControllers {
    @Autowired
    private AdminServicesImpl adminServices;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Admin admin) {
        try{
            String register = adminServices.register(admin);
            return new ResponseEntity<>(register,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{admin_id}")
    public ResponseEntity<HttpStatusCode> delete(@PathVariable String admin_id) {
            boolean deletion = adminServices.deletion(admin_id);
            if(deletion) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/view/{admin_id}")
    public ResponseEntity<Admin> view(@PathVariable String admin_id) {
        try {
            Admin viewAdmin = adminServices.viewAdmin(admin_id);
            return new ResponseEntity<>(viewAdmin,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login/{id}/{password}")
    public ResponseEntity<HttpStatusCode> login(@PathVariable String id, @PathVariable String password) {
            if(adminServices.login(id, password)) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/logout/{admin_id}")
    public ResponseEntity<HttpStatusCode> logout(@PathVariable String admin_id) {
        if(adminServices.logout(admin_id)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /*
    @PostMapping("/candidate-approve/{admin_id}/{cand_id}")
    public ResponseEntity<HttpStatusCode> approveCandidate(@PathVariable String admin_id, @PathVariable String cand_id) {
        adminServices.approveCandidate(admin_id,cand_id);
    }
    @PostMapping("/candidate-reject/{admin_id}/{cand_id}")
    public ResponseEntity<HttpStatusCode> rejectCandidate(@PathVariable String admin_id, @PathVariable String cand_id) {
        adminServices.rejectCandidate(admin_id,cand_id);
    }
     */
    @GetMapping("/get/candidate/constituency/{constituency_id}")
    public ResponseEntity<List<Candidate>> getCandidateConstituency(@PathVariable String constituency_id) {
        return new ResponseEntity<>(adminServices.getAllCandidatesByConstituency(constituency_id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/get/voter/constituency/{constituency_id}")
    public ResponseEntity<List<Voter>> getVoterConstituency(@PathVariable String constituency_id) {
        adminServices.getAllVoterByConstituency(constituency_id);
        return new ResponseEntity<>(adminServices.getAllVoterByConstituency(constituency_id), HttpStatus.ACCEPTED);
    }
    /*
    @PostMapping("/suspend-voter/{admin_id}/{epic_num}/{reason}")
    public ResponseEntity<HttpStatusCode> suspendVoter(@PathVariable String admin_id,@PathVariable String epic_num,@PathVariable String reason) {

    }
     */

}
