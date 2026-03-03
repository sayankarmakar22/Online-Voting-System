package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.Entity.Election;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepo extends JpaRepository<Election, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE election SET status = :status WHERE election_id = :id", nativeQuery = true)
    int closeElection(@Param("id") String id,
                      @Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE election SET status = :status WHERE election_id = :id", nativeQuery = true)
    int publishElection(@Param("id") String id,
                        @Param("status") String status);
}
