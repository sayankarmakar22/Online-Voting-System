package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.Entity.Constituency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstituencyRepo extends JpaRepository<Constituency, String> {
}
