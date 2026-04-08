package com.sayan.code.onlinevotingsystem.Controller;

import DTOs.DTOAdmin;
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

@RestController()
@RequestMapping("/v2/online-election/admin")
@Slf4j
public class AdminControllers {
    @Autowired
    private AdminServicesImpl adminServices;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody Admin admin)
    {
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
    public ResponseEntity<HttpStatusCode> delete(
            @PathVariable String admin_id)
    {
            boolean deletion = adminServices.deletion(admin_id);
            if(deletion) {
                log.info("Admin deleted: " + admin_id + LocalDateTime.now());
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<DTOAdmin> view(@PathVariable String id) {
        try {
            DTOAdmin viewAdmin = adminServices.viewAdmin(id);

            log.info("Admin view: {} , {}", id, LocalDateTime.now());

            return new ResponseEntity<>(viewAdmin, HttpStatus.ACCEPTED);

        } catch (Exception e) {

            log.error("Error while viewing admin: {} , {}", id, LocalDateTime.now(), e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login/{id}/{password}")
    public ResponseEntity<String> login(
            @PathVariable String id,
            @PathVariable String password,
            HttpSession session) {

        try {
            log.info("Started Login Process for: {}", id);

            boolean isValid = adminServices.login(id, password);

            if (isValid) {
                session.setAttribute("Admin_ID", id);
                session.setMaxInactiveInterval(1800);

                log.info("Login successful for: {} at {}", id, LocalDateTime.now());
                log.info("Session ID: {}", session.getId());

                return new ResponseEntity<>("Login Succeed", HttpStatus.ACCEPTED);
            }

            log.warn("Invalid login attempt for: {}", id);
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            log.error("Login failed for: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpSession session)
    {

        try{
            if(adminServices.logout(session.getAttribute("Admin_ID").toString())){
                log.info("Logout successful" + LocalDateTime.now());
                return new ResponseEntity<>("Logout Succeed",HttpStatus.ACCEPTED);
            }
        }
        catch (Exception e){
            log.info("Logout failed"+ LocalDateTime.now());
            return new ResponseEntity<>("Logout Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            session.removeAttribute("Admin_ID");
            session.invalidate();
            log.info("Delete Session " + LocalDateTime.now());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get/candidate/constituency/{constituency_id}")
    public ResponseEntity<List<Candidate>> getCandidateConstituency(
            @PathVariable String constituency_id)
    {
        return new ResponseEntity<>(adminServices.getAllCandidatesByConstituency(constituency_id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/get/voter/constituency/{constituency_id}")
    public ResponseEntity<List<Voter>> getVoterConstituency(
            @PathVariable String constituency_id)
    {
        adminServices.getAllVoterByConstituency(constituency_id);
        return new ResponseEntity<>(adminServices.getAllVoterByConstituency(constituency_id), HttpStatus.ACCEPTED);
    }

}
