package com.sayan.code.onlinevotingsystem.Entity;

import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {
    @Id
    private String admin_id;

    @Column(nullable = false,length = 30)
    private String admin_name;

    @Column(nullable = false,length = 2000)
    private String admin_password;

    @Column(unique = true,nullable = false,length = 300)
    private String admin_address;

    @Column(unique = true,nullable = false,length = 15)
    private String admin_phone;

    @Enumerated(EnumType.STRING)
    private Role role;
}
