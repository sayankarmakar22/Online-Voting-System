package com.sayan.code.onlinevotingsystem.Service.Implementation;

import com.sayan.code.onlinevotingsystem.Entity.AdminLoginDevice;
import com.sayan.code.onlinevotingsystem.Entity.VoterLoginDevice;
import com.sayan.code.onlinevotingsystem.Repository.AdminLoginDeviceRepo;
import com.sayan.code.onlinevotingsystem.Service.AdminLoginDeviceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminLoginDeviceServicesImpl implements AdminLoginDeviceServices {
    @Autowired
    private AdminLoginDeviceRepo adminLoginDeviceRepo;
    @Override
    public String setLoginTrue(String admin_id) {
        if(adminLoginDeviceRepo.existsById(admin_id)){
            return "You are allowed to login only from one device";
        }
        AdminLoginDevice adminLoginDevice = new AdminLoginDevice();
        adminLoginDevice.setAdmin_id(admin_id);
        adminLoginDevice.setLogin(true);
        adminLoginDevice.setLastLogin(new Date());
        adminLoginDeviceRepo.save(adminLoginDevice);

        return "Allowed";
    }

    @Override
    public String setLoginFalse(String admin_id) {
        if(adminLoginDeviceRepo.existsById(admin_id)){
            adminLoginDeviceRepo.deleteById(admin_id);
            return "logout";
        }
        return "logout failed";
    }
}
