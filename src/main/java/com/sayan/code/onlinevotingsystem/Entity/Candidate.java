package com.sayan.code.onlinevotingsystem.Entity;

import com.sayan.code.onlinevotingsystem.ENUMS.ActiveStatus;
import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@SuppressWarnings("ALL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candidate {
    @Id
    private String cand_id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 300)
    private String address;

    @Column(nullable = false, length = 15)
    private String dob;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    @Enumerated(EnumType.STRING)
    private ActiveStatus active;

    @ManyToOne
    @JoinColumn(name = "constituency_id")
    private Constituency constituency;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election_id;

    @OneToOne(mappedBy = "candidate")
    private VoteResult voteResult;
}
