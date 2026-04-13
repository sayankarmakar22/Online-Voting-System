package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.ENUMS.Status;
import com.sayan.code.onlinevotingsystem.Entity.Candidate;
import com.sayan.code.onlinevotingsystem.Entity.Election;
import com.sayan.code.onlinevotingsystem.Entity.VoteStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepo extends JpaRepository<Election, String> {

}
