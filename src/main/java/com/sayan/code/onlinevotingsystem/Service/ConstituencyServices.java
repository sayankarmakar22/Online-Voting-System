package com.sayan.code.onlinevotingsystem.Service;

import com.sayan.code.onlinevotingsystem.DTOs.DTOConstituency;
import com.sayan.code.onlinevotingsystem.Entity.Constituency;

import java.util.List;

public interface ConstituencyServices {
    String register(String name);
    DTOConstituency view(String id);
    DTOConstituency viewByName(String name);
    // future improvement -> implement pagination
    List<DTOConstituency> getAll();


}
