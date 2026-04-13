package com.sayan.code.onlinevotingsystem.Entity;

import com.sayan.code.onlinevotingsystem.ENUMS.ActiveStatus;
import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

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

    @Column(name = "phone_number",nullable = false,unique = true)
    private String phoneNumber;

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

    public Candidate(
            String id,
            String name,
            String address,
            String dob,
            Role role,
            String phnNum,
            Optional<Party> byId,
            ActiveStatus activeStatus,
            Optional<Constituency> byId1,
            Optional<Election> byId2)
    {
        this.cand_id = id;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.role = role;
        this.phoneNumber = phnNum;
        this.party = byId.isPresent() ? byId.get() : null;
        this.active = activeStatus;
        this.constituency = byId1.isPresent() ? byId1.get() : null;
        this.election_id = byId2.isPresent() ? byId2.get() : null;
    }

}
