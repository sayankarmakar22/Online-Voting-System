package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.Entity.Constituency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstituencyRepo extends JpaRepository<Constituency, String> {
//    @Query(name = "select voters_epic_id from user_constituency where constituencies_constituency_id=:id", nativeQuery = true)
//    List<String> getAllIdsById(String id);
}
