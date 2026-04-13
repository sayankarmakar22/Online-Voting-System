package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.DTOs.DTOConstituency;
import com.sayan.code.onlinevotingsystem.Entity.Constituency;
import com.sayan.code.onlinevotingsystem.Repository.ConstituencyRepo;
import com.sayan.code.onlinevotingsystem.Service.ConstituencyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConstituencyServicesImpl implements ConstituencyServices {

    @Autowired
    private ConstituencyRepo constituencyRepo;


    @Override
    public String register(String name) {
        Constituency constituency = new Constituency(
                UUID.randomUUID().toString().substring(0,3),
                name.toLowerCase()
        );
        return constituencyRepo.save(constituency).getConstituency_id();
    }

    @Override
    public DTOConstituency view(String id) {
        if(constituencyRepo.existsById(id)){
            Optional<Constituency> constituency = constituencyRepo.findById(id);
            return new DTOConstituency(
                    constituency.get().getConstituency_id(),
                    constituency.get().getConstituency_name(),
                    constituency.get().getCandidateList().size(),
                    constituency.get().getVoters().size()
            );
        }
        return null;
    }

    @Override
    public DTOConstituency viewByName(String name) {
        Constituency constituencyByConstituencyName = constituencyRepo.getConstituencyByConstituency_name(name);
        return new DTOConstituency(
                constituencyByConstituencyName.getConstituency_id(),
                constituencyByConstituencyName.getConstituency_name(),
                constituencyByConstituencyName.getCandidateList().size(),
                constituencyByConstituencyName.getVoters().size()
        );
    }

    @Override
    public List<DTOConstituency> getAll() {
        List<Constituency> allConstituency = constituencyRepo.findAll();

        List<DTOConstituency> constituencies = new ArrayList<>();

        for(Constituency constituency : allConstituency){

            constituencies.add(
                    new DTOConstituency(
                    constituency.getConstituency_id(),
                    constituency.getConstituency_name(),
                    constituency.getCandidateList().size(),
                    constituency.getVoters().size()
            ));
        }
        return constituencies;
    }
}
