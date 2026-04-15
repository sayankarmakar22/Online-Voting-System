package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.Entity.VoterLoginDevice;
import com.sayan.code.onlinevotingsystem.Repository.VoterLoginDeviceRepo;
import com.sayan.code.onlinevotingsystem.Service.VoterLoginDeviceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VoterLoginDeviceServicesImpl implements VoterLoginDeviceServices {
    @Autowired
    private VoterLoginDeviceRepo voterLoginDeviceRepo;

    @Override
    public String setLoginTrue(String epic_num) {
        if(voterLoginDeviceRepo.existsById(epic_num)){
            return "You are allowed to login only from one device";
        }
        VoterLoginDevice voterLoginDevice = new VoterLoginDevice();
        voterLoginDevice.setEpic_id(epic_num);
        voterLoginDevice.setLogin(true);
        voterLoginDevice.setLastLogin(new Date());
        voterLoginDeviceRepo.save(voterLoginDevice);

        return "Allowed";

    }

    @Override
    public String setLoginFalse(String epic_num) {
        if(voterLoginDeviceRepo.existsById(epic_num)){
            voterLoginDeviceRepo.deleteById(epic_num);
            return "logout";
        }
        return "logout failed";
    }
}
