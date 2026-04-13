package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.Entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRepo extends JpaRepository<Voter, String> {

//    @Query(name = "select * from user where epic_id in (select voters_epic_id from user_constituency where constituencies_constituency_id=:id)", nativeQuery = true)
//    List<Voter> getAllVoter(@Param("id") String id);

    @Query(value = "select * from voter where constituency_id =:consId",nativeQuery = true)
    List<Voter> getAllVoterByConId(@Param("consId") String consId);

    @Query(value = "select * from voter where phone_number=:ph", nativeQuery = true)
    Voter findVoterByPh_no(@Param("ph") String ph);

    Voter findByphoneNumber(String phoneNumber);

}
