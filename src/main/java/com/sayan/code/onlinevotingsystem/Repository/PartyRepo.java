package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.Entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepo extends JpaRepository<Party, String> {
}
