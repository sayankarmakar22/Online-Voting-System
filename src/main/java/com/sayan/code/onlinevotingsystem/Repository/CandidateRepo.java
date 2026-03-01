package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, String> {
}
