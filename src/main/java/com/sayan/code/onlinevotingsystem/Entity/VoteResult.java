package com.sayan.code.onlinevotingsystem.Entity;

import com.sayan.code.onlinevotingsystem.ENUMS.VoteResultStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VoteResult {
    @Id
    private String vote_result_id;

    @Enumerated(EnumType.STRING)
    private VoteResultStatus status;

    @Column(nullable = false,updatable = false)
    private BigInteger count;
    private Date created_at;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;



}
