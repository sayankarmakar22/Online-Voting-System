package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.Entity.VoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepo extends JpaRepository<VoteStatus, String> {

    @Query(value = "select * from vote where election_id=:election_id",nativeQuery = true)
    Object findByEpicNum(String election_id );

}
