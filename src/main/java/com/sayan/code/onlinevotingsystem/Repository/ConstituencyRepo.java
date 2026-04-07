package com.sayan.code.onlinevotingsystem.Repository;


import com.sayan.code.onlinevotingsystem.Entity.Constituency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstituencyRepo extends JpaRepository<Constituency,String> {

}
