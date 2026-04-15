package com.sayan.code.onlinevotingsystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.atn.SemanticContext;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class VoterLoginDevice {
    @Id
    private String epic_id;

    @Column(nullable = false)
    private boolean isLogin;

    @Column(nullable = false)
    private Date lastLogin;

}
