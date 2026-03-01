package com.sayan.code.onlinevotingsystem.Entity;

import com.sayan.code.onlinevotingsystem.ENUMS.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Vote {
    @Id
    private String vote_id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date created_at;

    @OneToOne
    @JoinColumn(name="epic_id",nullable = false,unique = true)
    private User epic_num;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election_id;

    @OneToOne
    @JoinColumn(name = "party_name")
    private Party party;



}
