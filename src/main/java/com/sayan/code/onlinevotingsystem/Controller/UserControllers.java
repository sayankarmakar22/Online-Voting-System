package com.sayan.code.onlinevotingsystem.Controller;

import com.sayan.code.onlinevotingsystem.DTOs.DTOVoter;
import com.sayan.code.onlinevotingsystem.DTOs.RegisterVoterDTO;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Service.Implementation.UserServicesImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/online-election/users")
@Slf4j
public class UserControllers {

    @Autowired
    private UserServicesImpl userServicesImpl;

    @PostMapping("/login/{epic_num}/{dob}")
    public ResponseEntity<String> userLogin(
            @PathVariable String epic_num,
            @PathVariable String dob,
            HttpSession session)
    {
            boolean login = userServicesImpl.login(epic_num, dob);
            if (login) {
                log.info("Voter Login : ", epic_num );
                session.setAttribute("Voter_EpicID", epic_num);
                session.setMaxInactiveInterval(600);
                log.info("Login Succeed", session.getAttribute("Voter_EpicID").toString());
                return new ResponseEntity<>("Login Succeed",HttpStatus.ACCEPTED);
            }
            log.error("Voter Login Failed", epic_num );
            return new ResponseEntity<>("Login Failed",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> userLogout(
            HttpSession session)
    {
        try{
            log.info("Starting logout process");
            log.info("Voter_EpicID : ", session.getAttribute("Voter_EpicID").toString());
            boolean logout = userServicesImpl.logout(session.getAttribute("Voter_EpicID").toString());
            if(logout){
                log.info("Voter Logout Succeed " + session.getAttribute("Voter_EpicID").toString());
                return new ResponseEntity<>("Logout Succeed",HttpStatus.ACCEPTED);
            }
        }
        catch (Exception e) {
                log.error("Voter Logout Failed" + session.getAttribute("Voter_EpicID").toString());
                return new ResponseEntity<>("Logout Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            session.removeAttribute("Voter_EpicID");
            session.invalidate();
        }
        return null;
    }

    @GetMapping("/profile")
    public ResponseEntity<DTOVoter> viewProfile(
            HttpSession session)
    {
        String epic_id = (String) session.getAttribute("Voter_EpicID");
        try{
            DTOVoter userProfile = userServicesImpl.viewProfile(epic_id);
            log.info("Viewing Voter_EpicID : {}", epic_id);
            return new ResponseEntity<>(userProfile,HttpStatus.FOUND);
        }
        catch (Exception e){
            log.error("error in viewProfile : {} ", epic_id);
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/profile/ph/{ph_no}")
    public ResponseEntity<DTOVoter> viewProfileByPh(
            @PathVariable String ph_no)
    {
        try{
            DTOVoter userProfile = userServicesImpl.viewProfileByPhone( ph_no);
            log.info("Viewing voter profile by using phone number {} : ", ph_no);
            return new ResponseEntity<>(userProfile,HttpStatus.FOUND);
        }
        catch (Exception e){
            log.error("Error while fetching voter profile by using phone number : {}", ph_no);
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<DTOVoter> userRegister(
            @RequestBody RegisterVoterDTO registerVoterDTO)
    {
        try{
            log.info("Onboarding Voter : {}", registerVoterDTO);
            DTOVoter registeredVoter = userServicesImpl.registerVoter(registerVoterDTO);
            log.info("Registered Voter" ,registeredVoter.getEpic_id() );
            return new ResponseEntity<>(registeredVoter, HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error("Registration Epic ID : ", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/election-details/{election_id}")
    public ResponseEntity<Election> viewElectionDetails(
            @PathVariable String election_id)
    {
        try {
            Election viewElectionDetails = userServicesImpl.viewElectionDetails(election_id);
            return new ResponseEntity<>(viewElectionDetails, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // change the candidate to DTOCandidate
    @GetMapping("/candidate-list")
    public ResponseEntity<List<Candidate>> viewCandidateList(
            HttpSession session)
    {
        try{
            List<Candidate> viewCandidateList =
                    userServicesImpl.viewCandidateList
                            (session.getAttribute("Voter_EpicID")
                                    .toString());

            return new ResponseEntity<>(viewCandidateList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/cast-vote")
    public ResponseEntity<HttpStatusCode> castVote(
            HttpSession session)
    {
        try{
            boolean castVote = userServicesImpl.castVote(session.getAttribute("Voter_EpicID").toString());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/history")
    public ResponseEntity<Object> viewHistory(
            HttpSession session)
    {
            try{
                Object userElectionHistory = userServicesImpl
                        .userElectionHistory(session
                                .getAttribute("Voter_EpicID")
                                .toString()
                        );
                return new ResponseEntity<>(userElectionHistory,HttpStatus.FOUND);
            }
            catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
    @GetMapping("/result")
    public ResponseEntity<List<Object>> result(
            HttpSession session)
    {
        try{
            List<Object> viewResult = userServicesImpl.ViewResult(
                    session.
                            getAttribute("Voter_EpicID").
                            toString()
            );
            return new ResponseEntity<>(viewResult,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/phone-number")
    public ResponseEntity<String> getUserPhoneNumber(
            HttpSession session)
    {
        try{
            String userPhoneNumber = userServicesImpl.userPhoneNumber(session.getAttribute("Voter_EpicID").toString());
            return new ResponseEntity<>(userPhoneNumber,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
