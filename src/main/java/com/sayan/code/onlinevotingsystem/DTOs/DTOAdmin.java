package com.sayan.code.onlinevotingsystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class DTOAdmin {
    private String id;
    private String name;
    private String address;
    private String phone;
}
