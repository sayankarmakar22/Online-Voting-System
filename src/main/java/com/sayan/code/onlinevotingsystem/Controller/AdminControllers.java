package com.sayan.code.onlinevotingsystem.Controller;

import com.sayan.code.onlinevotingsystem.DTOs.DTOAdmin;
import com.sayan.code.onlinevotingsystem.Entity.Admin;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Voter;
import com.sayan.code.onlinevotingsystem.Service.Implementation.AdminServicesImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v2/online-election/admin")
@Slf4j
public class AdminControllers {
    @Autowired
    private AdminServicesImpl adminServices;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Admin admin) {
        try{
            String register = adminServices.register(admin);
            log.info("Admin registered: " + register + LocalDateTime.now());
            return new ResponseEntity<>(register,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.info("Admin not registered: " + admin.getAdmin_id() + LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{admin_id}")
    public ResponseEntity<HttpStatusCode> delete(@PathVariable String admin_id) {
            boolean deletion = adminServices.deletion(admin_id);
            if(deletion) {
                log.info("Admin deleted: " + admin_id + LocalDateTime.now());
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/view")
    public ResponseEntity<DTOAdmin> view(
            HttpSession session)
    {
        String admin_id = (String) session.getAttribute("AdminID");
        try {
            log.info("Session ID : " , session.getId());
            log.info("Admin view: " + admin_id + LocalDateTime.now());
            DTOAdmin viewAdmin = adminServices.viewAdmin(admin_id);
            return new ResponseEntity<>(viewAdmin,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.info("Session ID : " , session.getId());
            log.info("Error while viewing admin : " + admin_id + LocalDateTime.now());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/login/{id}/{password}")
    public ResponseEntity<String> login(
            @PathVariable String id,
            @PathVariable String password,
            HttpSession session)

    {
        try{
            log.info("Login " + id);
            log.info("Session ID : " + session.getId());
            if(adminServices.login(id, password)) {
                log.info("Login successful"+ LocalDateTime.now());
                session.setAttribute("AdminID", id);
                session.setMaxInactiveInterval(1800);
                return new ResponseEntity<>("Login Succeed",HttpStatus.ACCEPTED);
            }
        }
        catch (Exception e){
            log.info("Session ID : " + session.getId());
            log.info("Error while login " + id + LocalDateTime.now());
            return new ResponseEntity<>("Login Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("Login failed"+ LocalDateTime.now());

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpSession session)
    {
        String admin_id = (String) session.getAttribute("AdminID");
        try{
            log.info("Session ID : " , session.getId());
            if(adminServices.logout(admin_id)) {
                log.info("Logout successful" + LocalDateTime.now());
                return new ResponseEntity<>("Logout Succeed",HttpStatus.ACCEPTED);
            }
        }
        catch (Exception e){
            log.info("Session ID : " , session.getId());
            log.info("Error while Logout " + admin_id + LocalDateTime.now());
            return new ResponseEntity<>("Logout failed ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            log.info("Session ID : " , session.getId());
            log.info("Deleting Session for : " + admin_id + LocalDateTime.now());
            session.removeAttribute("AdminID");
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get/candidate/constituency/{constituency_id}")
    public ResponseEntity<List<Candidate>> getCandidateConstituency(@PathVariable String constituency_id) {
        return new ResponseEntity<>(adminServices.getAllCandidatesByConstituency(constituency_id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/get/voter/constituency/{constituency_id}")
    public ResponseEntity<List<Voter>> getVoterConstituency(@PathVariable String constituency_id) {
        adminServices.getAllVoterByConstituency(constituency_id);
        return new ResponseEntity<>(adminServices.getAllVoterByConstituency(constituency_id), HttpStatus.ACCEPTED);
    }

}
