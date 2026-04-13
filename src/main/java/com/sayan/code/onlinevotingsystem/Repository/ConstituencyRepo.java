package com.sayan.code.onlinevotingsystem.Repository;


import com.sayan.code.onlinevotingsystem.Entity.Constituency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConstituencyRepo extends JpaRepository<Constituency,String> {

    @Query(value = "select * from constituency where constituency_name=:name",nativeQuery = true)
    Constituency getConstituencyByConstituency_name(@Param("name") String name);

}
