package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.Entity.VoterLoginDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface VoterLoginDeviceRepo extends JpaRepository<VoterLoginDevice, String> {
}
