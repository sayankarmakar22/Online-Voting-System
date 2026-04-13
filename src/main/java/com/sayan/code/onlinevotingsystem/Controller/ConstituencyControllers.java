package com.sayan.code.onlinevotingsystem.Controller;

import com.sayan.code.onlinevotingsystem.DTOs.DTOConstituency;
import com.sayan.code.onlinevotingsystem.Entity.Constituency;
import com.sayan.code.onlinevotingsystem.Repository.ConstituencyRepo;
import com.sayan.code.onlinevotingsystem.Service.Implementation.ConstituencyServicesImpl;
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
    private ConstituencyServicesImpl constituencyServices;

    @PostMapping("/register/{cons_name}")
    public ResponseEntity<String> registerConstituency(@PathVariable String cons_name)
    {
        log.info("Registering constituency, ", new Date().toString());
        String registered = constituencyServices.register(cons_name);
        return new ResponseEntity<>("Successfully registered , ID : " + registered, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOConstituency> getConstituencyById(
            @PathVariable String id)
    {
        log.info("Getting constituency by ID : ", id);
        DTOConstituency constituency = constituencyServices.view(id);
        return new ResponseEntity<>(constituency, HttpStatus.OK);
    }

    @GetMapping("/name/{cons_name}")
    public ResponseEntity<DTOConstituency> getByName(@PathVariable String cons_name)
    {
        return new ResponseEntity<>( constituencyServices.viewByName(cons_name),HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DTOConstituency>> getAllConstituency()
    {
        log.info("Getting all constituency, ", new Date().toString());
        List<DTOConstituency> constituencyList = constituencyServices.getAll();
        return new ResponseEntity<>(constituencyList, HttpStatus.OK);
    }
}
