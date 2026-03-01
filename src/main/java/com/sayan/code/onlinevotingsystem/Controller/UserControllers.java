package com.sayan.code.onlinevotingsystem.Controller;

import com.sayan.code.onlinevotingsystem.Service.Implementation.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/online-election/users")
public class UserControllers {

    @Autowired
    private UserServicesImpl userServicesImpl;

    @PostMapping("/login/{epic_num}/{dob}")
    public ResponseEntity<HttpStatusCode> userLogin(@PathVariable String epic_num,  @PathVariable String dob){
        try{
            userServicesImpl.login(epic_num,dob);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logout/{epic_num}")
    public ResponseEntity<HttpStatusCode> userLogout(@PathVariable String epic_num){
        try{
            userServicesImpl.logout(epic_num);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/election-details/{constituency_id}")
    public ResponseEntity<List<Object>> viewElectionDetails(@PathVariable String constituency_id){
        try {
            List<Object> viewElectionDetails = userServicesImpl.viewElectionDetails(constituency_id);
            return new ResponseEntity<>(viewElectionDetails, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/candidate-list/{constituency_id}")
    public ResponseEntity<List<Object>> viewCandidateList(@PathVariable String constituency_id){
        try{
            List<Object> viewCandidateList = userServicesImpl.viewCandidateList(constituency_id);
            return new ResponseEntity<>(viewCandidateList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile/{epic_num}")
    public ResponseEntity<Object> viewProfile(@PathVariable String epic_num){
        try{
            Object userProfile = userServicesImpl.viewProfile(epic_num);
            return new ResponseEntity<>(userProfile,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cast-vote/{epic_num}")
    public ResponseEntity<HttpStatusCode> castVote(@PathVariable String epic_num){
        try{
            HttpStatusCode castVote = userServicesImpl.castVote(epic_num);
            return new ResponseEntity<>(castVote);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/history/{epic_num}")
    public ResponseEntity<List<Object>> viewHistory(@PathVariable String epic_num){
        try{
            List<Object> userElectionHistory = userServicesImpl.userElectionHistory(epic_num);
            return new ResponseEntity<>(userElectionHistory,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/result/{constituency_id}")
    public ResponseEntity<List<Object>> result(@PathVariable String constituency_id){
        try{
            List<Object> viewResult = userServicesImpl.ViewResult(constituency_id);
            return new ResponseEntity<>(viewResult,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/phone-number/{epic_num}")
    public ResponseEntity<String> getUserPhoneNumber(@PathVariable String epic_num){
        try{
            String userPhoneNumber = userServicesImpl.userPhoneNumber(epic_num);
            return new ResponseEntity<>(userPhoneNumber,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
