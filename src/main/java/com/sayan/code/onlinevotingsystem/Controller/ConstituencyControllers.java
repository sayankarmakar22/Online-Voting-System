package com.sayan.code.onlinevotingsystem.Controller;

import com.sayan.code.onlinevotingsystem.Entity.Constituency;
import com.sayan.code.onlinevotingsystem.Repository.ConstituencyRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/online-voting/constituency")
@Slf4j
public class ConstituencyControllers {

    @Autowired
    private ConstituencyRepo constituencyRepo;

    @PostMapping("/register")
    public ResponseEntity<String> registerConstituency(@RequestBody Constituency constituency)
    {
        log.info("Registering constituency, ", new Date().toString());
        constituencyRepo.save(constituency);
        return new ResponseEntity<>("Successfully registered", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Constituency> getConstituencyById(
            @PathVariable String id)
    {
        log.info("Getting constituency by id, ", new Date().toString());
        Optional<Constituency> constituency = constituencyRepo.findById(id);
        return new ResponseEntity<>(constituency.orElse(new Constituency()), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Constituency>> getAllConstituency()
    {
        log.info("Getting all constituency, ", new Date().toString());
        List<Constituency> constituencies = constituencyRepo.findAll();
        return new ResponseEntity<>(constituencies, HttpStatus.OK);
    }
}
