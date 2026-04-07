package com.sayan.code.onlinevotingsystem.Entity;

import com.sayan.code.onlinevotingsystem.ENUMS.VoterStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VoteStatus")
public class VoteStatus {
    @Id
    private String vote_id;

    @Column(nullable = false)
    private String election_id;

    @Enumerated(EnumType.STRING)
    private VoterStatus voter_status;

    @Column(nullable = false, updatable = false)
    private Timestamp voted_at;

    @Column(nullable = false)
    private String transaction_hash;

    @Column(nullable = false)
    private String wallet_id;

    @OneToOne
    @JoinColumn(name = "epic_id")
    private Voter epic_id;

}
